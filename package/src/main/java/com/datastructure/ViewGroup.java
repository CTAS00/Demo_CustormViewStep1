package com.datastructure;

import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.View;

/**
 * Created by koudai_nick on 2018/3/12.
 */

public class ViewGroup extends View {
    public ViewGroup(Element element) {
        super(element);
    }

    @Override
    public float getPreferredSpan(int i) {
        return 0;
    }

    @Override
    public void paint(Graphics graphics, Shape shape) {

    }

    @Override
    public Shape modelToView(int i, Shape shape, Position.Bias bias) throws BadLocationException {
        return null;
    }

    @Override
    public int viewToModel(float v, float v1, Shape shape, Position.Bias[] biases) {
        return 0;
    }
}
