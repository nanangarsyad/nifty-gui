package de.lessvoid.nifty.loader.xpp3.elements;

import java.util.Map;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.ControlController;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.loader.xpp3.elements.helper.NiftyCreator;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.nifty.tools.TimeProvider;

/**
 * ImageType.
 * @author void
 */
public class TextType extends ElementType {

  /**
   * filename.
   * @required
   */
  private String text;

  /**
   * font.
   * @required
   */
  private String font;

  /**
   * color.
   */
  private ColorType color = new ColorType();

  /**
   * create it.
   * @param textParam filename
   * @param fontParam font
   */
  public TextType(final String textParam, final String fontParam) {
    this.text = textParam;
    this.font = fontParam;
  }

  /**
   * set text.
   * @param textParam text
   */
  public void setText(final String textParam) {
    this.text = textParam;
  }

  /**
   * set color.
   * @param colorParam color
   */
  public void setColor(final ColorType colorParam) {
    this.color = colorParam;
  }

  /**
   * create element.
   * @param parent parent
   * @param nifty nifty
   * @param screen screen
   * @param registeredEffects registeredEffects
   * @param registeredControls registeredControls
   * @param time time
   * @param screenController screenController
   * @return element
   */
  public Element createElement(
      final Element parent,
      final Nifty nifty,
      final Screen screen,
      final Map < String, RegisterEffectType > registeredEffects,
      final Map < String, RegisterControlDefinitionType > registeredControls,
      final TimeProvider time,
      final ControlController controlController,
      final ScreenController screenController) {
    TextRenderer textRenderer = NiftyCreator.createTextRenderer(nifty, color, text, font);
    PanelRenderer renderer = NiftyCreator.createPanelRenderer(
        nifty.getRenderDevice(),
        getBackgroundColor().createColor(),
        getBackgroundImage());
    Element panel = new Element(
        getId(),
        parent,
        screen,
        false,
        renderer,
        textRenderer);

    panel.setConstraintHeight(new SizeValue(textRenderer.getTextHeight() + "px"));
    panel.setConstraintWidth(new SizeValue(textRenderer.getTextWidth() + "px"));

    super.addElementAttributes(
        panel,
        screen,
        nifty,
        registeredEffects,
        registeredControls,
        time,
        controlController,
        screenController);

    parent.add(panel);
    return panel;
  }
}