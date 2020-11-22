package view;

import dao.CourseDao;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @author Administrator
 *     <p>窗口：程序主界面
 */
public class Window extends JFrame {
  CourseDao courseDao = new CourseDao();

  public Window() throws HeadlessException {
    // 设置主界面标题
    this.setTitle("课程管理");
    // 设置窗口弹出居中
    this.setLocationRelativeTo(null);
    // 设置窗口大小
    this.setSize(800, 400);
    // 设置关闭方式为程序退出
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 窗口布局
    this.setLayout(new BorderLayout());
    // 新建面板，并设置网格布局
    JPanel jPanel = new JPanel();
    jPanel.setLayout(new GridLayout(0, 1));
    // 在窗口左侧添加图片
    JLabel label = new JLabel();
    ImageIcon imageIcon = new ImageIcon("src/image/back2.jpg");
    label.setIcon(imageIcon);
    // 新建操作按钮
    JButton searchAllButton = new JButton("查询所有课程");
    JButton searchIdButton = new JButton("课程号查询");
    JButton searchNameButton = new JButton("课程名查询");
    JButton addButton = new JButton("添加课程");
    JButton deleteIdButton = new JButton("课程号删除");
    JButton deleteNameButton = new JButton("课程名删除");
    JButton updateButton = new JButton("更改课程信息");
    // 设置按钮的大小
    searchAllButton.setSize(50, 30);
    searchIdButton.setSize(50, 30);
    searchNameButton.setSize(50, 30);
    addButton.setSize(500, 30);
    deleteIdButton.setSize(50, 30);
    deleteNameButton.setSize(50, 30);
    updateButton.setSize(50, 30);
    // 查询所有课程 按钮 添加动作监听
    searchAllButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              // 弹出一个新的查询结果界面
              new SearchAll();
            } catch (SQLException throwables) {
              throwables.printStackTrace();
            }
          }
        });

    // 为根据Id搜索课程 按钮添加动作监听
    searchIdButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // 直到点击取消按钮或者叉，关闭窗口
            while (true) {
              // 弹出输入框，输入id，类型为String
              String id = JOptionPane.showInputDialog(null, "请输入课程号");
              // 如果没有输入，直接点击取消或者叉
              if (id == null) {
                // 弹出正在取消按钮
                JOptionPane.showMessageDialog(null, "正在取消……", "提示", JOptionPane.WARNING_MESSAGE);
                // 跳出循环，不再弹出窗口
                break;
              }
              // 如果没有输入而点击确认按钮
              else if ("".equals(id)) {
                // 弹出提示框：请输入课程号
                JOptionPane.showMessageDialog(null, "未输入课程号！", "提示", JOptionPane.WARNING_MESSAGE);
                // 继续循环
                continue;
              }
              // 有信息输入
              else {
                // 如果输入的是数字串
                if (isDigital(id)) {
                  Course result = null;
                  try {
                    // 将字符串转换成整形数字，查询数据库
                    result = courseDao.search(Integer.parseInt(id));
                  } catch (SQLException throwables) {
                    throwables.printStackTrace();
                  }
                  // 成功查询
                  if (result != null) {
                    // 弹出提示框，展示查询据俄国
                    JOptionPane.showMessageDialog(
                        null, result.getInfo(), "查询结果", JOptionPane.WARNING_MESSAGE);
                  } else {
                    // 没有查询到相关课程
                    JOptionPane.showMessageDialog(
                        null, "未查询到该课程", "查询结果", JOptionPane.WARNING_MESSAGE);
                  }
                }
                // 如果字符串不是数字串
                else {
                  // 弹出提示：输入的不是整型课程号
                  JOptionPane.showMessageDialog(
                      null, "输入的不是整形课程号，请重新输入！", "提示", JOptionPane.WARNING_MESSAGE);
                  continue;
                }
              }
            }
          }
        });

    // 为根据课程名查询 按钮添加动作监听
    searchNameButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            while (true) {
              // 弹出输入框，输入课程名
              String name = JOptionPane.showInputDialog(null, "请输入课程名");
              // 如果点击取消或者叉，弹出正在取消窗口，并退出
              if (name == null) {
                JOptionPane.showMessageDialog(null, "正在取消……", "提示", JOptionPane.WARNING_MESSAGE);
                break;
              }
              // 如果没有输入，点击了确定
              else if ("".equals(name)) {
                // 弹出提示框，没有输入课程名
                JOptionPane.showMessageDialog(null, "未输入课程名！", "提示", JOptionPane.WARNING_MESSAGE);
                continue;
              }
              // 如果有输入信息
              else {
                Course result = null;
                try {
                  // 根据课程名查询
                  result = courseDao.search(name);
                } catch (SQLException throwables) {
                  throwables.printStackTrace();
                }
                // 查询成功
                if (result != null) {
                  // 弹出窗口，展示查询结果
                  JOptionPane.showMessageDialog(
                      null, result.getInfo(), "查询结果", JOptionPane.WARNING_MESSAGE);
                } else {
                  // 查询失败，弹出提示框
                  JOptionPane.showMessageDialog(
                      null, "未查询到该课程", "查询结果", JOptionPane.WARNING_MESSAGE);
                }
              }
            }
          }
        });

    // 为新增按钮添加动作监听
    addButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            new AddCourse();
          }
        });

    // 为根据课程号删除  按钮  添加动作监听
    deleteIdButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            while (true) {
              // 弹出窗口，输入课程号
              String id = JOptionPane.showInputDialog(null, "请输入要删除的课程号");
              // 如果点击取消按钮或者叉，弹出正在取消界面
              if (id == null) {
                JOptionPane.showMessageDialog(null, "正在取消……", "提示", JOptionPane.WARNING_MESSAGE);
                break;
              }
              // 如果没有输入而点击确定，弹出提示框
              else if ("".equals(id)) {
                JOptionPane.showMessageDialog(null, "未输入课程号！", "提示", JOptionPane.WARNING_MESSAGE);
                continue;
              }
              // 如果有信息输入
              else {
                // 输入的字符串是数字串
                if (isDigital(id)) {
                  boolean result = false;
                  try {
                    // 根据课程号删除课程记录
                    result = courseDao.deleteCourse(Integer.parseInt(id));
                  } catch (SQLException throwables) {
                    throwables.printStackTrace();
                  }
                  // 删除成功
                  if (result) {
                    // 弹出提示框
                    JOptionPane.showMessageDialog(
                        null, "已查询到该课程，删除成功！", "查询结果", JOptionPane.WARNING_MESSAGE);
                  } else {
                    // 没有找到相应的课程  删除失败
                    JOptionPane.showMessageDialog(
                        null, "未查询到该课程，删除失败！", "查询结果", JOptionPane.WARNING_MESSAGE);
                  }
                } else {
                  // 如果输入的不是整形数字串 弹出提示窗口
                  JOptionPane.showMessageDialog(
                      null, "输入的不是整形课程号，请重新输入！", "提示", JOptionPane.WARNING_MESSAGE);
                  continue;
                }
              }
            }
          }
        });

    // 为根据课程名删除添加动作监听
    deleteNameButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            while (true) {
              // 弹出窗口   输入课程名
              String name = JOptionPane.showInputDialog(null, "请输入要删除的课程名");
              // 如果点击取消或者叉
              if (name == null) {
                // 弹出正在取消窗口
                JOptionPane.showMessageDialog(null, "正在取消……", "提示", JOptionPane.WARNING_MESSAGE);
                break;
              }
              // 如果没有输入  而点击了确认
              else if ("".equals(name)) {
                // 弹出  输入课程名提示框
                JOptionPane.showMessageDialog(null, "未输入课程名！", "提示", JOptionPane.WARNING_MESSAGE);
                continue;
              }
              // 有信息输入
              else {
                boolean result = false;
                try {
                  // 根据课程名删除课程记录
                  result = courseDao.deleteCourse(name);
                } catch (SQLException throwables) {
                  throwables.printStackTrace();
                }
                // 删除成功
                if (result) {
                  // 成功提示框
                  JOptionPane.showMessageDialog(
                      null, "已查询到该课程，删除成功！", "查询结果", JOptionPane.WARNING_MESSAGE);
                } else {
                  // 没有找到相应课程    删除课程失败
                  JOptionPane.showMessageDialog(
                      null, "未查询到该课程，删除失败！", "查询结果", JOptionPane.WARNING_MESSAGE);
                }
              }
            }
          }
        });

    // 为  更改课程信息  添加动作监听
    updateButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            // 弹出一个新的窗口
            new UpdateCourse();
          }
        });

    // 将各个按钮添加到面板中
    jPanel.add(searchNameButton);
    jPanel.add(searchIdButton);
    jPanel.add(addButton);
    jPanel.add(deleteNameButton);
    jPanel.add(deleteIdButton);
    jPanel.add(updateButton);
    jPanel.add(searchAllButton);

    // 将图片标签添加到窗口
    this.add(label);
    // 将面板添加到窗口的右侧
    this.add(jPanel, BorderLayout.EAST);
    // 设置窗口可见
    this.setVisible(true);
  }

  /** 判断字符串是否为整形数字串 */
  public static boolean isDigital(String string) {
    for (int i = 0; i < string.length(); i++) {
      // 如果不是'0'-'9'，则不是数字串，返回false
      if (!(string.charAt(i) >= 48 && string.charAt(i) <= 57)) {
        return false;
      }
    }
    return true;
  }
}
