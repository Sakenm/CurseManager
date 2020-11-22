package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import model.Course;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

/** @author Administrator */
public class CourseDao {
  /** 获取连接池dataSource */
  ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
  /** 创建queryRunner */
  QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
  /** 根据课程名搜索 */
  public Course search(String name) throws SQLException {
    Course course = null;
    String sql = "select * from 课程 where name = ?";
    // SQL查询  返回一个Bean对象
    course = queryRunner.query(sql, new BeanHandler<Course>(Course.class), name);
    return course;
  }

  /** 根据课程号搜索 */
  public Course search(int id) throws SQLException {
    Course course = null;
    String sql = "select * from 课程 where id = ?";
    // SQL查询  返回一个Bean对象
    course = queryRunner.query(sql, new BeanHandler<Course>(Course.class), id);
    return course;
  }

  /** 查询所有的课程记录 */
  public List<Course> searchAll() throws SQLException {
    List<Course> courses;
    String sql = "select * from 课程";
    // SQL查询  返回List容器
    courses = queryRunner.query(sql, new BeanListHandler<Course>(Course.class));
    return courses;
  }

  /** 增加一条课程记录 */
  public boolean addCourse(Course course) throws SQLException {
    String sql = "insert into 课程(id,name,studyTime, studyGrade) values(?,?,?,?)";
    // SQL查询  返回受影响的行数
    int row =
        queryRunner.update(
            sql, course.getId(), course.getName(), course.getStudyTime(), course.getStudyGrade());
    // 如果受影响的行数为正整数，那么添加记录成功
    if (row > 0) {
      return true;
    } else {
      return false;
    }
  }

  /** 根据id删除一条课程记录 */
  public boolean deleteCourse(int id) throws SQLException {
    String sql = "delete from 课程 where id = ?";
    // SQL查询  返回受影响的行数
    int row = queryRunner.update(sql, id);
    // 如果受影响的行数为正整数，那么删除记录成功
    if (row > 0) {
      return true;
    } else {
      return false;
    }
  }

  /** 根据课程名删除一条记录 */
  public boolean deleteCourse(String string) throws SQLException {
    String sql = "delete from 课程 where name = ?";
    // SQL查询  返回受影响的行数
    int row = queryRunner.update(sql, string);
    // 如果受影响的行数为正整数，那么删除记录成功
    if (row > 0) {
      return true;
    } else {
      return false;
    }
  }

  /** 更改一条记录 */
  public boolean updateCourse(int id, Course course) throws SQLException {
    String sql = "update 课程 set id = ? ,name = ? ,studyTime = ?, studyGrade = ? where id = ?";
    // SQL查询  返回受影响的行数
    int row =
        queryRunner.update(
            sql,
            course.getId(),
            course.getName(),
            course.getStudyTime(),
            course.getStudyGrade(),
            id);
    // 如果受影响的行数为正整数，那么更改记录成功
    if (row > 0) {
      return true;
    } else {
      return false;
    }
  }
}
