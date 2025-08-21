package components;


import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class TextAreaRenderer extends JTextArea implements TableCellRenderer {

		public TextAreaRenderer() {
            setLineWrap(true);           // Cho phép xuống dòng
            setWrapStyleWord(true);      // Xuống dòng theo từ
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Đặt văn bản cho ô
            setText(value != null ? value.toString() : "");
            
            // Điều chỉnh màu nền khi ô được chọn
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }

            // Đặt chiều cao hàng dựa trên nội dung của JTextArea
            int height = getPreferredSize().height;
            if (table.getRowHeight(row) < height) {
                table.setRowHeight(row, height);
            }
            
            return this;
        }
    }