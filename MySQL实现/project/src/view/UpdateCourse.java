package view;

import dao.CourseDao;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/** @author Administrator 窗口：更新课程信息 */
public class UpdateCourse extends JFrame {
  public UpdateCourse() {
    // 设置窗口弹出位置居中
    this.setLocationRelativeTo(null);
    // 设置窗口名称
    this.setTitle("修改课程信息");
    // 窗口布局
    this.setLayout(new BorderLayout());
    // 声明文本域对象
    JTextField id, newId, newName, newStudyTime, newStudyGrade;
    // 提示标签
    JLabel jl0 = new JLabel("原课程号：");
    // 用来输入的文本域
    id = new JTextField(10);
    JLabel jl1 = new JLabel("课程号:");
    newId = new JTextField(10);
    JLabel jl2 = new JLabel("课程名:");
    newName = new JTextField(10);
    JLabel jl3 = new JLabel("学时:");
    newStudyTime = new JTextField(10);
    JLabel jl4 = new JLabel("学分:");
    newStudyGrade = new JTextField(10);
    JPanel jp = new JPanel(new GridLayout(5, 2));
    // 将标签和文本域添加到面板中
    jp.add(jl0);
    jp.add(id);
    jp.add(jl1);
    jp.add(newId);
    jp.add(jl2);
    jp.add(newName);
    jp.add(jl3);
    jp.add(newStudyTime);
    jp.add(jl4);
    jp.add(newStudyGrade);
    // 确认按钮
    JButton jb = new JButton("确认");
    // 为确认按钮添加动作监听
    jb.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // 如果没有输入完整信息，比如课程名没有填写等
            if ("".equals(id.getText())
                || "".equals(newId.getText())
                || "".equals(newName.getText())
                || "".equals(newStudyTime.getText())
                || "".equals(newStudyGrade.getText())) {
              // 弹出窗口，提示输入完整信息
              JOptionPane.showMessageDialog(
                  null, "信息不完整，请填写好信息", "提示", JOptionPane.WARNING_MESSAGE);
            } else {
              // 原课程号和课程号输入不是整形数字串
              if ((!Window.isDigital(id.getText())) || (!Window.isDigital(newId.getText()))|| (!Window.isDigital(newStudyTime.getText()))|| (!Window.isDigital(newStudyGrade.getText()))) {
                JOptionPane.showMessageDialog(
                    null, "原课程号or课程号or学时or学分输入的不是整形课程号，请重新输入！", "提示", JOptionPane.WARNING_MESSAGE);
              } else {
                // 信息输入完成，进行更改操作
                CourseDao courseDao = new CourseDao();
                // 将输入的信息封装成一个Bean对象
                Course newCourse = new Course();
                newCourse.setId(Integer.parseInt(newId.getText()));
                newCourse.setName(newName.getText());
                newCourse.setStudyTime(Integer.parseInt(newStudyTime.getText()));
                newCourse.setStudyGrade(Integer.parseInt(newStudyGrade.getText()));
                boolean result = false;
                try {
                  // 执行更新语句，利用原课程号找到课程记录，再进行更改
                  result = courseDao.updateCourse(Integer.parseInt(id.getText()), newCourse);
                } catch (SQLException throwables) {
                  throwables.printStackTrace();
                }
                // 如果修改成功，将输入文本域清空，以便继续修改
                if (result) {
                  // 弹出提示框，修改成功
                  JOptionPane.showMessageDialog(null, "修改成功！", "结果", JOptionPane.WARNING_MESSAGE);
                  id.setText("");
                  newId.setText("");
                  newName.setText("");
                  newStudyGrade.setText("");
                  newStudyTime.setText("");
                } else {
                  // 课程表的课程号为主键，因此不能有重复的课程号，这里弹出添加失败
                  JOptionPane.showMessageDialog(
                      null,
                      "没有课程号为" + id.getText() + "的课程，修改失败！",
                      "结果",
                      JOptionPane.WARNING_MESSAGE);
                }
              }
            }
          }
        });
    // 将面板添加进窗口
    this.add(jp);
    // 将按钮添加到窗口底部
    this.add(jb, BorderLayout.SOUTH);
    // 自动调整窗口大小
    this.pack();
    // 设置窗口可见
    this.setVisible(true);
  }
}
