package view;

import dao.CourseDao;
import model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/** @author Administrator 窗口：查询所有的课程并显示 */
public class SearchAll extends JFrame {
  CourseDao courseDao = new CourseDao();

  public SearchAll() throws SQLException {
    // 设置窗口弹出位置居中
    this.setLocationRelativeTo(null);
    // 设置窗口名称
    this.setTitle("查询所有课程");
    // 窗口布局
    this.setLayout(new BorderLayout());
    JPanel jPanel = new JPanel();
    // 面板布局
    jPanel.setLayout(new BorderLayout());
    // 利用表格来展示查询到的数据库结果
    JTable jTable = getTable();
    // 表格分成表头和表尾来添加到面板
    jPanel.add(jTable.getTableHeader(), BorderLayout.NORTH);
    jPanel.add(jTable, BorderLayout.CENTER);
    jPanel.add(jTable);
    this.add(jPanel);
    // 窗口自动调整大小
    this.pack();
    // 设置窗口可见
    this.setVisible(true);
  }

  /** 从数据库中读取数据，放在一个表格里，方法返回一个表格 */
  public JTable getTable() throws SQLException {
    List<Course> lists;
    // 从数据库查询所有课程，返回一个List
    lists = courseDao.searchAll();
    // 表格的列名
    Object[] columnNames = {"课程号", "课程名", "学时", "学分"};
    // 表格的数据区域（表尾）
    Object[][] data = new Object[lists.size()][4];
    for (int i = 0; i < lists.size(); i++) {
      data[i][0] = lists.get(i).getId();
      data[i][1] = lists.get(i).getName();
      data[i][2] = lists.get(i).getStudyTime();
      data[i][3] = lists.get(i).getStudyGrade();
    }
    JTable jTable = new JTable(data, columnNames);
    // 设置表格数据居中显示
    DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
    defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    jTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
    return jTable;
  }
}