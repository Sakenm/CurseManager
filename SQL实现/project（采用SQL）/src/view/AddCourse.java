package view;

import dao.CourseDao;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *  @author Administrator
 *  窗口：添加课程
 *
 *  */
public class AddCourse extends JFrame {
  public AddCourse() {
    // 设置窗口弹出位置居中
    this.setLocationRelativeTo(null);
    // 设置窗口名称
    this.setTitle("添加课程信息");
    JTextField id, name, studyTime, studyGrade;
    // 提示标签
    JLabel jl1 = new JLabel("课程号:");
    // 输入框
    id = new JTextField(10);
    JLabel jl2 = new JLabel("课程名:");
    name = new JTextField(10);
    JLabel jl3 = new JLabel("学时:");
    studyTime = new JTextField(10);
    JLabel jl4 = new JLabel("学分:");
    studyGrade = new JTextField(10);
    // 设置主面板为4*2的网格布局
    JPanel jp = new JPanel(new GridLayout(4, 2));
    // 将各个组件加入到面板中
    jp.add(jl1);
    jp.add(id);
    jp.add(jl2);
    jp.add(name);
    jp.add(jl3);
    jp.add(studyTime);
    jp.add(jl4);
    jp.add(studyGrade);
    JButton jb = new JButton("确认");
    JLabel jlinfo;
    jlinfo = new JLabel("课程信息", JLabel.CENTER);
    // 为“确认”按钮添加动作监听
    jb.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // 如果没有输入完整信息，比如课程名没有填写等
            if ("".equals(id.getText())
                || "".equals(name.getText())
                || "".equals(studyGrade.getText())
                || "".equals(studyTime.getText())) {
              // 弹出窗口，提示输入完整信息
              JOptionPane.showMessageDialog(
                  null, "信息不完整，请填写好信息", "提示", JOptionPane.WARNING_MESSAGE);
            } else {
              if ((!Window.isDigital(id.getText())) || (!Window.isDigital(studyTime.getText()))|| (!Window.isDigital(studyGrade.getText()))) {
                JOptionPane.showMessageDialog(
                        null, "课程号or学时or学分输入的不是整形课程号，请重新输入！", "提示", JOptionPane.WARNING_MESSAGE);
              }else{
                // 将输入的信息封装成对象
                Course newCourse = new Course();
                newCourse.setId(Integer.parseInt(id.getText()));
                newCourse.setName(name.getText());
                newCourse.setStudyTime(Integer.parseInt(studyTime.getText()));
                newCourse.setStudyGrade(Integer.parseInt(studyGrade.getText()));
                boolean result = false;
                try {
                  // 创建dao层实例化对象
                  CourseDao courseDao = new CourseDao();
                  // 执行添加课程方法，返回布尔值
                  result = courseDao.addCourse(newCourse);
                } catch (SQLException throwables) {
                  throwables.printStackTrace();
                }
                // 如果添加成功，将文本域清空，以便继续添加
                if (result) {
                  // 弹出添加成功提示框
                  JOptionPane.showMessageDialog(null, "添加成功！", "结果", JOptionPane.WARNING_MESSAGE);
                  id.setText("");
                  name.setText("");
                  studyTime.setText("");
                  studyGrade.setText("");
                } else {
                  // 课程表的课程号为主键，因此不能有重复的课程号，这里弹出添加失败
                  JOptionPane.showMessageDialog(
                          null, "已有课程号为" + id.getText() + "的课程，添加失败！", "结果", JOptionPane.WARNING_MESSAGE);
                }
              }
            }
          }
        });

    //添加面板
    this.add(jp);
    this.add(jlinfo, BorderLayout.NORTH);
    //在底部添加确认按钮
    this.add(jb, BorderLayout.SOUTH);
    //窗口自动调整大小
    this.pack();
    //设置窗口可见
    this.setVisible(true);
  }
}
