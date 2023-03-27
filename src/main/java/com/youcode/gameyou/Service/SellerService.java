package com.youcode.gameyou.Service;

import com.youcode.gameyou.DTO.SellerDTO;
import com.youcode.gameyou.Entity.Seller;
import com.youcode.gameyou.Exception.ApiException;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Repository.SellerRepository;
import com.youcode.gameyou.Service.Interfaces.ISellerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SellerService implements ISellerService {
    private final UploadFileService uploadFileService;
    private final AuthService authService;
    private final SellerRepository sellerRepository;
    private final Mapper<SellerDTO, Seller> mapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public SellerDTO save(SellerDTO addNewSellerDTO) {
        // check if email is already exist or throw exception
        sellerRepository.findByEmail(addNewSellerDTO.getEmail())
                        .orElseThrow(() -> new ApiException("Seller already exist", HttpStatus.BAD_REQUEST));
        Seller seller = mapper.convertAtoB(addNewSellerDTO, Seller.class); // map sellerDTO to seller
        sellerRepository.save(seller); // save seller
        SellerDTO sellerDTO = mapper.convertBtoA(seller, SellerDTO.class); // map seller to sellerDTO
        return sellerDTO;
    }

    @Override
    public SellerDTO updateInfo(SellerDTO updateSellerInfoDTO, Long id) {
        // find the seller
        Seller findSellerById = sellerRepository.findById(id).orElseThrow(() -> new ApiException("Seller not found", HttpStatus.BAD_REQUEST));
        SellerDTO sellerDTO = mapper.convertBtoA(findSellerById, SellerDTO.class); // map seller to sellerDTO

        // check if the password match the password in the database
        Boolean checkIsPasswordMatch = passwordEncoder.matches(updateSellerInfoDTO.getHashedPassword(), sellerDTO.getHashedPassword());

        if(!checkIsPasswordMatch) throw new RuntimeException("password not match");

        if(updateSellerInfoDTO.getFirstName() != null) sellerDTO.setFirstName(updateSellerInfoDTO.getFirstName());
        if(updateSellerInfoDTO.getLastName() != null) sellerDTO.setLastName(updateSellerInfoDTO.getLastName());
        if(updateSellerInfoDTO.getEmail() != null) sellerDTO.setEmail(updateSellerInfoDTO.getEmail());

        // map sellerDTO to seller
        Seller seller = mapper.convertAtoB(sellerDTO, Seller.class);
        sellerRepository.save(seller); // save seller

        return sellerDTO;
    }

    @Override
    public void delete(Long id) {
        // find the seller and update isActive to false
        Seller findSeller = sellerRepository.findById(id).orElseThrow(() -> new ApiException("Seller not found", HttpStatus.BAD_REQUEST));
        // map seller to sellerDTO
        SellerDTO sellerDTO = mapper.convertBtoA(findSeller, SellerDTO.class);
        sellerDTO.setIsActive(false);
        // map sellerDTO to seller
        Seller seller = mapper.convertAtoB(sellerDTO, Seller.class);
        sellerRepository.save(seller);
    }

    @Override
    public SellerDTO getOne(Long id) {
        // find the seller
        Seller seller = sellerRepository.findById(id).orElseThrow(() -> new ApiException("Seller not found", HttpStatus.BAD_REQUEST));
        SellerDTO sellerDTO = mapper.convertBtoA(seller, SellerDTO.class);
        return sellerDTO;
    }

    @Override
    public List<SellerDTO> getAll(int page, int size) {
        if(page > 0) page--;
        List<Seller> sellers = sellerRepository.findAll(PageRequest.of(page, size)).stream().toList();
        // map sellers to sellerDTOS
        List<SellerDTO> sellerDTOS = mapper.convertListBToListA(sellers, SellerDTO.class);
        return sellerDTOS;
    }

    @Override
    public String uploadImage (MultipartFile imageFile) {
        if(imageFile == null) throw new RuntimeException("image is null");

        Seller seller = authService.getAuthenticatedSeller();
        if (seller == null) throw new RuntimeException("seller not found");

        String path = uploadFileService.getOnePath(imageFile);
        if (path == null || path.equals("")) throw new RuntimeException("File not found");

        seller.setImagePath(path);
        sellerRepository.save(seller);
        return path;
    }
}