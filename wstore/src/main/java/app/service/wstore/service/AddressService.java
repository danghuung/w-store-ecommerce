package app.service.wstore.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.service.wstore.dto.AddressDto;
import app.service.wstore.entity.Address;
import app.service.wstore.entity.CustomUserDetails;
import app.service.wstore.entity.User;
import app.service.wstore.exception.ErrorResponse;
import app.service.wstore.exception.NotFoundException;
import app.service.wstore.exception.UnauthorizedException;
import app.service.wstore.repository.AddressRepository;
import app.service.wstore.repository.UserRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get List Address of currentUser
    public List<AddressDto> getList(CustomUserDetails currentUser) {
        User user = userRepository.getUser(currentUser);
        List<Address> addresses = addressRepository.findByUser(user);

        ArrayList<AddressDto> result = new ArrayList<AddressDto>();

        for (Address address : addresses) {
            result.add(modelMapper.map(address, AddressDto.class));
        }
        return result;
    }

    // Get Detail Address of currenUser
    public AddressDto getDetail(int id, CustomUserDetails currentUser) {
        User user = userRepository.getUser(currentUser);

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address Id not exist!"));

        if (address.getUser().equals(user)) {
            return modelMapper.map(address, AddressDto.class);
        }

        throw new UnauthorizedException("You Dont Have Permisstion!");
    }

    // Create Address
    public AddressDto create(AddressDto addressDto, CustomUserDetails currentUser) {
        User user = userRepository.getUser(currentUser);

        Address address = modelMapper.map(addressDto, Address.class);
        address.setUser(user);

        return modelMapper.map(addressRepository.save(address), AddressDto.class);
    }

    // Update Address
    public AddressDto update(int id, AddressDto addressDto, CustomUserDetails currentUser) {
        User user = userRepository.getUser(currentUser);
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address Id not exist!"));

        if (address.getUser().equals(user)) {
            addressDto.setId(id);
            Address newAddress = modelMapper.map(addressDto, Address.class);

            return modelMapper.map(addressRepository.save(newAddress), AddressDto.class);
        }

        throw new UnauthorizedException("You Dont Have Permisstion!");
    }

    // Delete Address
    public ErrorResponse delete(int id, CustomUserDetails currentUser) {
        User user = userRepository.getUser(currentUser);

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address Id not exist!"));

        if (address.getUser().equals(user)) {
            addressRepository.delete(address);
            return null;
        }
        throw new UnauthorizedException("You Dont Have Permisstion!");
    }
}
