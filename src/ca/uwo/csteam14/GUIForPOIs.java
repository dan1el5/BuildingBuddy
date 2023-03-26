package ca.uwo.csteam14;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class GUIForPOIs {

    protected AppMenu appMenu = new AppMenu("user");
    protected static Canvas secondary;
    protected static JLabel title = new JLabel();

    protected static MapView mapView;

    public GUIForPOIs(String POIsGroup, String POIType) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
            POI poi = null;
            switch (POIType) {
                case "Bookmarks" -> poi = Data.bookmarks.getFirst();
                case "My Locations" -> poi = Data.userCreatedPOIs.getFirst();
                case "Developer Tool" -> poi = Data.builtInPOIs.getFirst();
                case "Search & Discovery" -> poi = Search.firstResult;
            }

            try {
                if (poi == null) poi = Data.builtInPOIs.getFirst();
                secondary = new Canvas("./images/" + poi.map.replaceAll("\\dF.png", "") + "_hero.png");
                mapView = new MapView(poi.map, new Point(poi.positionX, poi.positionY));
                title = new JLabel("<html><div style=\"text-align:center;\">" +
                        POIType + "<br /></div></html>");
                // Set the font size and style
                title.setFont(new Font("Arial", Font.BOLD, 26));
                // Set the foreground color
                Color foregroundColour = new Color(75, 250, 0);
                Color background = new Color(0, 0, 0, 0.3f);
                padding(title);
                title.setForeground(foregroundColour);
                title.setOpaque(true);
                title.setBackground(background);
                secondary.load(title, 'L');
                GUI.frame.setContentPane(secondary);
                new POISelector(POIsGroup);
                new Search();
                BuildingBuddy.currentBuildingCode = poi.code;
                BuildingBuddy.currentFloor = poi.map.replaceAll(".png", "");
                LayerFilter.paintAllIcons();
                GUI.frame.pack();
                GUI.frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void padding(JLabel label) {
        label.setBorder(BorderFactory.createEmptyBorder(7, 50, 7, 50));
        // Set the preferred size of the JLabel to include the padding
        Dimension size = label.getPreferredSize();
        size.width += 10;
        size.height += 5;
        label.setPreferredSize(size);
    }
}