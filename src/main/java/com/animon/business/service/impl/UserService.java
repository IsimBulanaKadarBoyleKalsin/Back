package com.animon.business.service.impl;

import com.animon.business.dto.UserDto;
import com.animon.business.service.IUserService;
import com.animon.configuration.beanconfig.ModelMapperBean;
import com.animon.configuration.beanconfig.PasswordEncoderBean;
import com.animon.data.entity.RoleEntity;
import com.animon.data.entity.UserEntity;
import com.animon.data.repository.IRoleRepository;
import com.animon.data.repository.IUserRepository;
import com.animon.exception.MyCustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import com.animon.exception.Resource404NotFoundException;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService<UserDto, UserEntity> {

    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final ModelMapperBean modelMapper;
    private final PasswordEncoderBean passwordEncoder;

    @Override
    public UserDto entityToDto(UserEntity userEntity) {
        return modelMapper.modelMapperMethod().map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity dtoToEntity(UserDto userDto) {
        return modelMapper.modelMapperMethod().map(userDto, UserEntity.class);
    }


    @Override
    @Transactional
    public UserDto userServiceCreate(Long rolesId, UserDto userDto) {

        if (userDto != null && roleRepository.findAll().size() != 0) {
            UserEntity userEntity = dtoToEntity(userDto);

            // Masking Password
            userEntity.setPassword(passwordEncoder.passwordEncoderMethod().encode(userDto.getUserPassword()));

            // Add role

            int roleIdMatch = Integer.valueOf(Math.toIntExact(rolesId));
            RoleEntity roleEntity = roleRepository.findAll().get(roleIdMatch -1);
            Set<RoleEntity> roleEntitySet = new HashSet<>();
            roleEntitySet.add(roleEntity);
            userEntity.setRoleEntities(roleEntitySet);

            // Save
            userRepository.save(userEntity);

            // Set on id
            userDto.setUserId(userEntity.getUserId());

            return userDto;

        }
        return null;
    }

    @Override
    public List<UserDto> userServiceList() {
        Iterable<UserEntity> userEntityIterable = userRepository.findAll();

        List<UserDto> userDtoList = new ArrayList<>();

        for (UserEntity userEntity : userEntityIterable) {
            userDtoList.add(entityToDto(userEntity));
        }


        return userDtoList;
    }

    @Override
    @SneakyThrows
    public UserDto userServiceFindById(Long id) {

        Boolean doesUserExist = userRepository.findByUserId(id).isPresent();
        UserEntity userEntity = null;
        if (doesUserExist) {
            userEntity = userRepository.findByUserId(id).orElseThrow(
                    () -> new Resource404NotFoundException("Can not find id:" + id)
            );
        } else if (!doesUserExist) {
            throw new MyCustomException("User Dto id value is empty");
        }
        return entityToDto(userEntity);
    }

    @Override
    @Transactional
    public UserDto userServiceUpdateById(Long id, UserDto userDto) {
        if (userDto != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserNickName(userDto.getUserNickName());
            userEntity.setUserName(userDto.getUserName());
            userEntity.setUserSurname(userDto.getUserSurname());
            userEntity.setUserEmail(userDto.getUserEmail());
            userEntity.setPassword(userDto.getUserPassword());

            userRepository.save(userEntity);
            return entityToDto(userEntity);
        }
        return null;
    }

    @Override
    public UserDto userServiceDeleteById(Long id) {
        UserDto userFindDto = userServiceFindById(id);
        if (userFindDto != null) {
            userRepository.deleteById(id);
        }
        return userFindDto;
    }
}
