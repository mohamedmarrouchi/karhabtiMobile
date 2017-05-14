/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeDeLaRoute;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Form;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;

/**
 *
 * @author HANY
 */
public class PieChart6 extends AbstractChart{
     /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Budget chart";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The budget per project for this year (pie chart)";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Form execute() {
    double[] values = new double[] { 13, 44 };
    int[] colors = new int[] { ColorUtil.WHITE, ColorUtil.BLACK };
    final DefaultRenderer renderer = buildCategoryRenderer(colors);
    
    renderer.setChartTitleTextFont(largeFont);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    renderer.setBackgroundColor(0x3399ff);
    renderer.setApplyBackgroundColor(true);
    renderer.setLabelsColor(ColorUtil.WHITE);
    renderer.setLabelsTextSize(72);
    
    final CategorySeries seriesSet = buildCategoryDataset("test", values);
    final com.codename1.charts.views.PieChart chart = new com.codename1.charts.views.PieChart(seriesSet, renderer);
    ChartComponent comp = new ChartComponent(chart){

        private boolean inDrag = false;
        
        

        

        
        
        @Override
        protected void seriesReleased(SeriesSelection sel) {
            
            if ( inDrag ){
                // Don't do this if it was a drag operation
                return;
            }
            
            for ( SimpleSeriesRenderer r : renderer.getSeriesRenderers()){
                r.setHighlighted(false);
            }
            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(sel.getPointIndex());
            r.setHighlighted(true);
            
            Shape seg = chart.getSegmentShape(sel.getPointIndex());
            Rectangle bounds = seg.getBounds();
            bounds = new Rectangle(
                    bounds.getX()-40,
                    bounds.getY()-40,
                    bounds.getWidth()+80,
                    bounds.getHeight()+80
            );
            
            this.zoomToShapeInChartCoords(bounds, 500);
            
            
            
        }
       
        
        
    };
    comp.setZoomEnabled(true);
    comp.setPanEnabled(true);
    comp.getStyle().setBgColor(0xff0000);
    comp.getStyle().setBgTransparency(255);
    
    return wrap("Niveau un", comp);
    
  }

}
