package co.edu.umanizales.manage_storemy.controller;

import co.edu.umanizales.manage_storemy.controller.dto.ResponseDTO;
import co.edu.umanizales.manage_storemy.model.Seller;
import co.edu.umanizales.manage_storemy.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getSellers()
    {
        return new ResponseEntity<>(new ResponseDTO(
                200,sellerService.getSellers(),null
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createSeller(@RequestBody Seller seller){
        Seller findSeller = sellerService.getSellerById(seller.getCode());
        if(findSeller == null){
            sellerService.addSeller(seller);
            return new ResponseEntity<>(new ResponseDTO(200 ,"vendedor agregador", null
        ),HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO(
                    409,  "ya existe un vendedor con este codigo", null
            ),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/{code}")
    public ResponseEntity<ResponseDTO> getSellerById(@PathVariable String code)
    {
        Seller findSeller = sellerService.getSellerById(code);
        if (findSeller == null)
        {
            return new ResponseEntity<>(new ResponseDTO(
                    200, findSeller, null
            ), HttpStatus.OK);
        }
        else
        {
        return new ResponseEntity<>(new ResponseDTO(
                200, findSeller, null
        ), HttpStatus.OK);
        }
    }

}
