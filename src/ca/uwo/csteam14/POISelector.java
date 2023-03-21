package ca.uwo.csteam14;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class POISelector extends JPanel {
    protected JScrollPane scrollPane;
    protected LinkedList<POI> currentCollection;

    public POISelector(LinkedList<POI> collection) {
        currentCollection = collection;
        ArrayList<String> items = new ArrayList<>();
        ArrayList<String> pois = new ArrayList<>();
        for (POI poi: collection) {
            items.add(poi.name + " (" + poi.building +")");
            pois.add(String.valueOf((Integer)poi.id));
        }
        JList<String> itemList = new JList<>(items.toArray(new String[items.size()]));

        DefaultListCellRenderer renderer = new CustomListCellRenderer();
        renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        itemList.setCellRenderer(renderer);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                System.out.println(pois.get(itemList.getSelectedIndex()));
            }
        });
        scrollPane = new JScrollPane(itemList);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setPreferredSize(new Dimension(400,400));
        GUIForPOIs.secondary.load(scrollPane, 'L');
    }

    public void refreshPOISelector() {
        new POISelector(currentCollection);
    }

    public static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {

            // Call the parent class to get the default renderer
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Customize the appearance of the cell
            if (isSelected) {
                c.setBackground(new Color(209, 204, 255));
                c.setForeground(Color.BLACK);
            }
            else {
            c.setBackground(Color.WHITE);
            c.setForeground(Color.BLACK);
            }

            c.setFont(new Font("Arial", Font.PLAIN, 18));
            Insets insets = new Insets(10, 20, 5, 20);
            ((JComponent) c).setBorder(new EmptyBorder(insets));

            // Return the customized cell
            return c;
        }
    }
}
