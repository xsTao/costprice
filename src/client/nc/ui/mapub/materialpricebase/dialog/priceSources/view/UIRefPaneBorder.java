package nc.ui.mapub.materialpricebase.dialog.priceSources.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;

import javax.swing.JComponent;
import javax.swing.border.AbstractBorder;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.style.Style;

public class UIRefPaneBorder extends AbstractBorder {

    private static final long serialVersionUID = 5526311998257716657L;

    private Insets insets = new Insets(1, 4, 1, 1);

    public UIRefPaneBorder() {
        super();
    }

    public UIRefPaneBorder(Insets insets) {
        super();
        this.insets = insets;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return this.insets;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        Color color = Style.getCtrBorderInactiveBackGround();

        if (c instanceof UIRefPane) {
            UIRefPane ref = (UIRefPane) c;

            if (ref.getUIButton().isEnabled()) {
                color = Style.getCtrBorderActiveBackGround();
            }
            else {
                color = Style.getCtrBorderInactiveBackGround();
            }

        }
        else {

            color = Style.getCtrBorderActiveBackGround();

        }

        if (c.isFocusOwner()) {
            color = Style.getCtrBorderFocusBackground();
        }

        Shape shape = this.getRoundShape(x, y, width, height, this.insets);
        this.paintCtrlBorder((Graphics2D) g, (JComponent) c, c.getBackground(), color, shape, 1);
        this.paintCornerPoint((Graphics2D) g, (JComponent) c, x, y, width, height);
    }

    public Shape getRoundShape(int x, int y, int width, int height, Insets insets) {
        GeneralPath path = new GeneralPath();
        int offset = 3;
        path.moveTo(x + offset - 1, y);
        path.lineTo(width - offset, y);
        path.curveTo(width - offset, y, width - offset + 1, y + 1, width - offset + 2, y + 2);
        path.lineTo(width - offset + 2, height - offset);
        path.curveTo(width - offset + 2, height - offset, width - offset + 1, height - offset + 1, width - offset,
                height - offset + 2);
        path.lineTo(x + offset - 1, height - offset + 2);
        path.curveTo(x + offset - 1, height - offset + 2, x + offset - 2, height - offset + 1, x + offset - 3, height
                - offset);
        path.lineTo(x, y + offset - 1);
        path.curveTo(x, y + offset - 1, x + 1, y + offset - 2, x + 2, y + offset - 3);
        return path;
    }

    public void paintCtrlBorder(Graphics2D g, JComponent c, Color fillColor, Color borderColor, Shape shape, int w) {
        Object oldValue = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        Color oldColor = g.getColor();
        Paint oldPaint = g.getPaint();
        Stroke oldStroke = g.getStroke();
        BasicStroke bs = new BasicStroke(w);
        g.setStroke(bs);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(borderColor);
        g.draw(shape);
        g.setColor(oldColor);
        g.setPaint(oldPaint);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldValue);
        g.setStroke(oldStroke);

    }

    private void paintCornerPoint(Graphics2D g, Component c, int x, int y, int width, int height) {
        Color oldColor = g.getColor();
        g.setColor(c.getBackground());

        g.drawLine(1, 2, 1, 2);
        g.drawLine(2, 1, 2, 1);

        g.drawLine(1, height - 3, 1, height - 3);
        g.drawLine(2, height - 2, 2, height - 2);

        g.drawLine(width - 3, y + 1, width - 3, y + 1);
        g.drawLine(width - 2, y + 2, width - 2, y + 2);

        g.drawLine(width - 2, height - 3, width - 2, height - 3);
        g.drawLine(width - 3, height - 2, width - 3, height - 2);
        g.drawLine(1, 2, 1, 2);

        g.setColor(Color.WHITE);
        g.drawLine(1, 2, 1, height - 4);
        g.drawLine(2, 1, 2, height - 2);
        g.drawLine(3, 1, 3, height - 2);
        g.drawLine(4, 1, 4, height - 2);

        g.setColor(oldColor);

    }
}
