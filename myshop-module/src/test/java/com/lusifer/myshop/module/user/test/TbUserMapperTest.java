package com.lusifer.myshop.module.user.test;

import com.lusifer.myshop.common.utils.IDUtils;
import com.lusifer.myshop.module.user.entity.TbUser;
import com.lusifer.myshop.module.user.mapper.TbUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath:spring-context.xml"})
public class TbUserMapperTest {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setId(IDUtils.genId());
        tbUser.setUsername("admin999");
        tbUser.setEmail("admin999@admin.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("admin999".getBytes()));
        tbUser.setPhone("15888888886");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserMapper.insert(tbUser);
    }

    @Test
    public void testGetById() {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(1L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void testMD5() {
        System.out.println(DigestUtils.md5DigestAsHex("admin999".getBytes()));
    }
}
