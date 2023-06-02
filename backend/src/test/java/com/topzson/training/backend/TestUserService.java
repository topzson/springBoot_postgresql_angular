package com.topzson.training.backend;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.topzson.training.backend.entity.Address;
import com.topzson.training.backend.entity.Social;
import com.topzson.training.backend.entity.User;
import com.topzson.training.backend.exception.BaseException;
import com.topzson.training.backend.exception.UserException;
import com.topzson.training.backend.services.AddressService;
import com.topzson.training.backend.services.SocialService;
import com.topzson.training.backend.services.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class TestUserService {

    @Autowired
    private UserService userService;
    private AddressService addressService;
    private SocialService socialService;

    @Order(1)
    @Test
    void testCreate() throws BaseException {
        User user = userService.create(
                TestCreateData.email,
                TestCreateData.password,
                TestCreateData.name

        );
        // check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        // check equals
        Assertions.assertEquals(TestCreateData.email, user.getEmail());

        boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
        Assertions.assertTrue(isMatched);

        Assertions.assertEquals(TestCreateData.name, user.getName());

    }

    @Test
    @Order(2)
    void testUpdate() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);

        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());

    }

    @Test
    @Order(3)
    void testCreateSocial() throws UserException {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        Social social = user.getSocial();
        Assertions.assertNull(social);
        social = socialService.create(
                user,
                SocialTestCreatedata.facebook,
                SocialTestCreatedata.line,
                SocialTestCreatedata.instagram,
                SocialTestCreatedata.tiktok

        );
        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreatedata.facebook, social.getFacebook());

    }

    @Order(4)
    @Test
    void testCreateAddress() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        List<Address> addresses = user.getAddresses();
        Assertions.assertTrue(addresses.isEmpty());

        createAddress(user, AddressTestCreateData.line1, AddressTestCreateData.line2, AddressTestCreateData.zipcode);
        createAddress(user, AddressTestCreateData2.line1, AddressTestCreateData2.line2, AddressTestCreateData2.zipcode);

    }

    private void createAddress(User user, String line1, String line2, String zipcode) {
        Address address = addressService.create(user, line1, line2, zipcode);

        Assertions.assertNotNull(address);
        Assertions.assertEquals(line1, address.getLine1());
        Assertions.assertEquals(line2, address.getLine2());
        Assertions.assertEquals(zipcode, address.getZipcode());
    }

    @Test
    @Order(9)
    void testDelete() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        Social social = user.getSocial();
        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreatedata.facebook, social.getFacebook());

        List<Address> addresses = user.getAddresses();
        Assertions.assertFalse(addresses.isEmpty());
        Assertions.assertEquals(2, addresses.size());

        userService.deleteById(user.getId());

        Optional<User> optDalete = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(optDalete.isEmpty());

    }

    interface TestCreateData {
        String email = "GG@game.com";
        String password = "asssaasdqwe1121sd";
        String name = "asdasd";
    }

    interface SocialTestCreatedata {
        String facebook = "topzson";
        String line = "";
        String instagram = "";
        String tiktok = "";

    }

    interface AddressTestCreateData {
        String line1 = "12345";
        String line2 = "Muu";
        String zipcode = "32112";
    }

    interface AddressTestCreateData2 {

        String line1 = "456/7";

        String line2 = "Muang";

        String zipcode = "37001";

    }

    interface TestUpdateData {
        String name = "Topzsonupdate";
    }

}
