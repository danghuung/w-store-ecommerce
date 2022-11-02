package app.service.wstore.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.service.wstore.dto.DiscountCodeDto;
import app.service.wstore.entity.DiscountCode;
import app.service.wstore.exception.DuplicateRecordException;
import app.service.wstore.exception.NotFoundException;
import app.service.wstore.repository.DiscountCodeRepository;

@Service
public class DiscountCodeService {

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get List Discount Codes for role Customer
    public List<DiscountCodeDto> getListForCustomer() {
        List<DiscountCode> discountCodes = discountCodeRepository.findAll();
        ArrayList<DiscountCodeDto> result = new ArrayList<DiscountCodeDto>();

        for (DiscountCode discountCode : discountCodes) {
            if (discountCode.getIsActive() == true) {
                result.add(modelMapper.map(discountCode, DiscountCodeDto.class));
            }
        }
        return result;
    }

    // Get List Discount codes for role ADMIN
    public List<DiscountCodeDto> getListForAdmin() {
        List<DiscountCode> discountCodes = discountCodeRepository.findAll();
        ArrayList<DiscountCodeDto> result = new ArrayList<DiscountCodeDto>();

        for (DiscountCode discountCode : discountCodes) {
            result.add(modelMapper.map(discountCode, DiscountCodeDto.class));

        }
        return result;
    }

    // Create DiscountCode
    public DiscountCodeDto create(DiscountCodeDto discountCodeDto) {
        DiscountCode discountCode = modelMapper.map(discountCodeDto, DiscountCode.class);

        if (discountCodeRepository.existsByCode(discountCodeDto.getCode())) {
            throw new DuplicateRecordException("Code is exist");
        }

        if (discountCodeDto.getCode() == "") {
            discountCode.setCode(randomCode());
        }

        return modelMapper.map(discountCodeRepository.save(discountCode), DiscountCodeDto.class);
    }

    // Update code
    public DiscountCodeDto update(int id, DiscountCodeDto discountCodeDto) {
        discountCodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Code Id not exist!"));

        discountCodeDto.setId(id);
        DiscountCode newCode = modelMapper.map(discountCodeDto, DiscountCode.class);

        return modelMapper.map(discountCodeRepository.save(newCode), DiscountCodeDto.class);
    }

    // Delete Code
    public void delete(int id) {
        discountCodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Code Id not exist!"));
        discountCodeRepository.deleteById(id);
    }

    public String randomCode() {
        return RandomStringUtils.randomAlphabetic(6);
    }
}
