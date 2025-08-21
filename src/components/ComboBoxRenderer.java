package components;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComboBoxRenderer extends JComboBox<String> implements TableCellRenderer {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public ComboBoxRenderer(JComboBox<String> comboBox) {
	        super();
	        for (int i = 0; i < comboBox.getItemCount(); i++) {
	            this.addItem(comboBox.getItemAt(i));
	        }
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        if (isSelected) {
	            setBackground(table.getSelectionBackground());
	        } else {
	            setBackground(table.getBackground());
	        }
	        setSelectedItem(value);
	        return this;
	    }
	}