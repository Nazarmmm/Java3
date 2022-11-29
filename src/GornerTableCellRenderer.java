

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private String needle = null;
    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();

    public GornerTableCellRenderer() {

        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(col == 2){

            label.setText(String.valueOf(value));

            //if(String.valueOf(value) == "true"){
                panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            //}

            panel.setBackground(Color.white);
            return panel;
        }

        String formattedDouble = formatter.format(value);
        label.setText(formattedDouble);

        if (col == 1 && needle != null && needle.equals(formattedDouble)) {
            panel.setBackground(Color.RED);
        } else {
            panel.setBackground(Color.white);

            double num = Double.valueOf(label.getText());
            int i = 0;
            String str = String.valueOf(num);
            while(i < str.length() && str.charAt(i++) != '.'){}
            for(; i < str.length(); i++){
                if(str.charAt(i) == '1' || str.charAt(i) == '3' || str.charAt(i) == '5'){
                    panel.setBackground(Color.orange);
                } else {
                    panel.setBackground(Color.white);
                    break;
                }
            }
        }

        return panel;
    }
    public void setNeedle(String needle) {
        this.needle = needle;
    }
}

