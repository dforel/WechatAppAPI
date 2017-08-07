import com.df.dao.UserbasisDao;
import com.df.model.UserbasisEntity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017-08-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
public class testDao {


    @Resource
    @Autowired
    private UserbasisDao userbasisDao;


    @org.junit.Test
    @Rollback(true)
    public void testDeleteIP() throws Exception {
        UserbasisEntity ss= userbasisDao.findByName("dforel");
        System.out.println(ss.getPassword());
    }
}
