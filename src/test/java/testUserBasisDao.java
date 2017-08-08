import com.df.dao.UserbasisDao;
import com.df.model.UserbasisEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
public class testUserBasisDao {


    @Resource
    @Autowired
    private UserbasisDao userbasisDao;

    @Test
    //@Rollback(true)
    public void testFindbyName() throws Exception {
        UserbasisEntity ss= userbasisDao.findByName("dforel");
        Assert.assertNotNull(ss);
        Assert.assertEquals(893497472280825856l,ss.getId());
        Assert.assertEquals("dforel",ss.getName());
        Assert.assertEquals("13671177326",ss.getPhone());
        //System.out.println(ss.getPassword());
    }

    @Test
    //@Rollback(true)
    public void testLogin() throws Exception {
        Integer num= userbasisDao.countByPhoneAndPassword("13671177327","7c4879d19074f576f0ba124f81f59a73");
        Assert.assertEquals(new Integer(1),num);

        UserbasisEntity entity= userbasisDao.findFirstByPhoneAndPassword("13671177327","7c4879d19074f576f0ba124f81f59a73");
        Assert.assertNotNull(entity);
        Assert.assertEquals(893497472280825858l,entity.getId());
        Assert.assertEquals("sasa",entity.getName());
        Assert.assertEquals("13671177327",entity.getPhone());

        UserbasisEntity entityWrongPW= userbasisDao.findFirstByPhoneAndPassword("13671177327","1234");
        Assert.assertNull(entityWrongPW);

        //System.out.println(ss.getPassword());
    }

    @Test
    //@Rollback(true)
    public void testExist() throws Exception {
        Integer ss= userbasisDao.countByName("dforel");
        System.out.println(ss);
        Assert.assertEquals(new Integer(1),ss);
        Integer ss1= userbasisDao.countByName("dforel3");
        System.out.println(ss1);
        Assert.assertEquals(new Integer(0) ,ss1);
        //System.out.println(ss.getPassword());
    }

    @Test
    //@Rollback(true)
    public void testExist1() throws Exception {

        //matcher好像不是很好用

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT)
                .withIgnorePaths("id")
                .withIncludeNullValues();

        UserbasisEntity entity = new UserbasisEntity();
        entity.setName("dforel");

        System.out.println(matcher.getIgnoredPaths());
        System.out.println(matcher.getPropertySpecifiers());
        System.out.println(matcher.getNullHandler());
        System.out.println(matcher.getDefaultStringMatcher());

        boolean ss= userbasisDao.exists( Example.of(entity,matcher) );
//
        System.out.println(ss);
        //Assert.assertEquals(true,ss);
//


        UserbasisEntity entity2 = new UserbasisEntity();
        entity2.setName("dforel3");
//
        boolean ss1= userbasisDao.exists(Example.of(entity2));
        System.out.println(ss1);
        Assert.assertEquals(false ,ss1);
        //System.out.println(ss.getPassword());
    }

    @Test
    //@Rollback(true)
    public void testExist2() throws Exception {

        Map<String,Object> map = new HashMap<>();
        map.put("name","dforel");
        map.put("phone","13671177326");
        //map.put("id",893497472280825858l);

        // l
        Specification<UserbasisEntity> sp = (root, criteriaQuery, criteriaBuilder) -> {
            for (Map.Entry<String, Object> entry : map.entrySet()){
                Path<String> nameExp = root.get(entry.getKey());
                Predicate pr =   criteriaBuilder.equal(nameExp, entry.getValue());
                criteriaQuery.where(pr);
            }
            return null;
        };
        List ss= userbasisDao.findAll(sp);

        System.out.println(ss.size());
        //Assert.assertEquals(true,ss);
//
        UserbasisEntity entity2 = new UserbasisEntity();
        entity2.setName("dforel3");
//
        boolean ss1= userbasisDao.exists(Example.of(entity2));
        System.out.println(ss1);
        Assert.assertEquals(false ,ss1);
        //System.out.println(ss.getPassword());
    }

    /**
     * 注册方法
     * @throws Exception
     */
    @Test
    @Rollback(true)
    public void testSave() throws Exception {
        UserbasisEntity entity = new UserbasisEntity();
        entity.setName("sasa");
        entity.setEmail("asda@qq.sa");
        entity.setPhone("13671177327");
        entity.setPassword("7c4879d19074f576f0ba124f81f59a73");
        entity.setStatus(1);
        entity.setPermission(1);
        entity.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        entity.setCreatetime(new Timestamp(System.currentTimeMillis()));
        userbasisDao.save(entity);
        //如果id不为零就算成功了
        Assert.assertNotEquals(0l,entity.getId());

        System.out.println(entity.getId());
        //System.out.println(ss.getPassword());
    }

    /**
     * 修改
     * @throws Exception
     */
    @Test
    @Rollback(true)
    public void testUpdate() throws Exception {
        UserbasisEntity entity = new UserbasisEntity();
        entity.setId(893497472280825858l);
        entity.setName("修改名字");
        entity.setEmail("修改邮箱");
        entity.setPhone("13671177325");
        entity.setPassword("22222");
        entity.setStatus(1);
        entity.setPermission(1);
        entity.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        entity.setCreatetime(new Timestamp(System.currentTimeMillis()));
        userbasisDao.save(entity);
        //如果id不为零就算成功了
        Assert.assertEquals("修改名字",entity.getName());
        Assert.assertEquals("修改邮箱",entity.getEmail());
        Assert.assertEquals("22222",entity.getPassword());
        Assert.assertNotEquals(0l,entity.getId());

        System.out.println(entity.getId());
        //System.out.println(ss.getPassword());
    }

}
