package com.youcode.gameyou.Controller;

import com.youcode.gameyou.DTO.SellerDTO;
import com.youcode.gameyou.DTO.UserDTO;
import com.youcode.gameyou.Mapper.Mapper;
import com.youcode.gameyou.Request.Seller.UpdateSellerInfoRequest;
import com.youcode.gameyou.Request.UpdatePasswordRequest;
import com.youcode.gameyou.Response.Seller.SellerResponse;
import com.youcode.gameyou.Service.SellerService;
import com.youcode.gameyou.Service.UpdatePassword;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;
    private final UpdatePassword updatePassword;
    private final Mapper<SellerDTO, SellerResponse> mapper;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/updateInfo/{id}")
    public SellerResponse updateInfo (@RequestBody @Valid UpdateSellerInfoRequest updateSellerInfoRequest, @PathVariable Long id) {
        // map updateSellerInfoRequest to SellerDTO
        SellerDTO sellerDTO = new SellerDTO();
        BeanUtils.copyProperties(updateSellerInfoRequest, sellerDTO);
        SellerDTO sellerDTOAfterUpdate = sellerService.updateInfo(sellerDTO, id);
        // map sellerDTO to sellerResponse
        SellerResponse sellerResponse = mapper.convertAtoB(sellerDTO, SellerResponse.class);
        return sellerResponse;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/updatePassword/{id}")
    public SellerResponse updatePassword (@RequestBody @Valid UpdatePasswordRequest updateClientPassword, @PathVariable Long id) {
        // map updateSellerPassword to sellerDTO
        UserDTO userDTO = updatePassword.updatePassword(updateClientPassword, "seller", id);
        // map userDTO to sellerDTO
        SellerDTO sellerDTO = new SellerDTO();
        BeanUtils.copyProperties(userDTO, sellerDTO);
        SellerResponse sellerResponse = mapper.convertAtoB(sellerDTO, SellerResponse.class);
        return sellerResponse;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/updateImageProfile")
    public String updateImageProfile(@RequestParam MultipartFile imageFile) {
        // update image profile and return the path of the image
        return sellerService.uploadImage(imageFile);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        sellerService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public SellerResponse getOne (@PathVariable Long id) {
        SellerDTO sellerDTO = sellerService.getOne(id);
        // map sellerDTO to sellerResponse
        SellerResponse sellerResponse = mapper.convertAtoB(sellerDTO, SellerResponse.class);
        return sellerResponse;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<SellerResponse> getAll (@RequestParam int page, @RequestParam int size) {
        List<SellerDTO> sellerDTOS = sellerService.getAll(page, size);
        // map sellerDTOS to sellerResponses
        List<SellerResponse> sellerResponses = mapper.convertListAToListB(sellerDTOS, SellerResponse.class);
        return sellerResponses;
    }
}