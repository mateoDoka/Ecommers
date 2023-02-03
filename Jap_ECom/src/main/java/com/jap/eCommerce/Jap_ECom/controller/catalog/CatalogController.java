package com.jap.eCommerce.Jap_ECom.controller.catalog;

import com.jap.eCommerce.Jap_ECom.constants.AppsConstants;
import com.jap.eCommerce.Jap_ECom.exception.AppsException;
import com.jap.eCommerce.Jap_ECom.model.common.ResponseDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.catalog.ProductDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.catalog.ProductFilterRQ;
import com.jap.eCommerce.Jap_ECom.service.catalog.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @PostMapping(value = "/addProduct", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<ProductDTO>> addProduct(@RequestBody ProductDTO saveProductDTO) {
        ResponseDTO<ProductDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        ProductDTO productDTO;
        try {
            productDTO = this.catalogService.addProduct(saveProductDTO);

            response.setResult(productDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping(value = "/changeProduct/{productID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<ProductDTO>> changeProduct(@RequestBody ProductDTO changeProductDTO, @PathVariable Long productID) {
        ResponseDTO<ProductDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        ProductDTO productDTO;
        try {
            productDTO = this.catalogService.changeProduct(changeProductDTO, productID);

            response.setResult(productDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/deleteProduct/{productID}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteProduct(@PathVariable Long productID) {
        ResponseDTO<Boolean> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            this.catalogService.deleteProduct(productID);
            response.setResult(true);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            response.setResult(false);
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/getProductData/{productID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<ProductDTO>> getProductData(@PathVariable Long productID) {
        ResponseDTO<ProductDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            ProductDTO productDTO = this.catalogService.getProductData(productID);

            response.setResult(productDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping(value = "/searchCatalog", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> searchCatalog(@RequestBody ProductFilterRQ filterRQ) {
        ResponseDTO<List<ProductDTO>> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            List<ProductDTO> productDTOList = this.catalogService.searchCatalog(filterRQ);

            response.setResult(productDTOList);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
