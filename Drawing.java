import java.util.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.geom.*;

import javax.swing.*;
import java.util.List;

public class Drawing extends JComponent {
    private Integer x;
    private Integer y;
    private Integer param1;
    private Integer param2;
    private Integer param3;
    private String shape;
    private String color;
    private String Param1;
    private String Param2;
    private String Param3;

    private Integer[] tri_pointerArray;

    public Drawing(String[] componentArray) {

        String[] address = componentArray[0].split(",");
        this.x = Integer.parseInt(address[0]);
        this.y = Integer.parseInt(address[1]);
        this.shape = componentArray[1];
        this.color = componentArray[2];
        this.Param1 = componentArray[3];
        this.Param2 = componentArray[4];
        this.Param3 = componentArray[5];

        try {
            if (isNum(Param1) || isNum(Param2)) {

                param1 = Integer.parseInt(Param1);
                param2 = Integer.parseInt(Param2);

            } else {

                List<Integer> tri_pointerList = new ArrayList<Integer>();
                makeTriPoint(Param1, tri_pointerList);
                makeTriPoint(Param2, tri_pointerList);
                makeTriPoint(Param3, tri_pointerList);
                Integer[] toArray = new Integer[tri_pointerList.size()];
                toArray = tri_pointerList.toArray(toArray);
                this.tri_pointerArray = toArray;
            }

        } catch (NumberFormatException e) {

        }
    }

    public static boolean isNum(String num) {
        if (num == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }

    public void makeTriPoint(String param, List<Integer> list) {
        param = param.replace(")", "");
        param = param.replace("(", "");
        String[] params = param.split(",");
        Integer a = Integer.parseInt(params[0]);
        Integer b = Integer.parseInt(params[1]);
        list.add(a);
        list.add(b);
    }

    private Color Colorfill;

    public Color PaintColor(String color) {
        if (color.equals("red")) {
            Colorfill = new Color(255, 0, 0);
        } else if (color.equals("green")) {
            Colorfill = new Color(0, 255, 0);
        } else if (color.equals("blue")) {
            Colorfill = new Color(0, 0, 255);
        } else if (color.equals("pink")) {
            Colorfill = new Color(255, 182, 193);
        }

        return Colorfill;
    }

    public void shape(Graphics g, Integer x, Integer y, String shape, String color, Integer param1, Integer param2,
            Integer[] tri_pointerArray) {
        Graphics2D g2d = (Graphics2D) g;

        if (shape.equals("square")) {

            Rectangle2D.Double r = new Rectangle2D.Double(x, y, param1, param1);
            g2d.setColor(PaintColor(color));
            g2d.fill(r);

        } else if (shape.equals("circle")) {

            g2d.setColor(PaintColor(color));
            g.fillOval(x, y, param1, param1);

        } else if (shape.equals("rect")) {

            g2d.setColor(PaintColor(color));
            g.fillRect(x, y, param1, param2);

        } else if (shape.equals("triangle")) {

            g2d.setColor(PaintColor(color));
            int[] x_point = { tri_pointerArray[0] + x, tri_pointerArray[2] + x, tri_pointerArray[4] + x };
            int[] y_point = { tri_pointerArray[1] + y, tri_pointerArray[3] + y, tri_pointerArray[5] + y };
            g.fillPolygon(x_point, y_point, 3);

        }
    }
}
