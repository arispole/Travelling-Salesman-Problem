import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
	
public class PathCostChart extends ApplicationFrame {
	
	protected DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

	public PathCostChart(String applicationTitle, String chartTitle) {
		
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, 
				"Generations",
				"Path Cost",
				dataset,
				PlotOrientation.VERTICAL,
				true,true,false);
		         
		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 1500 , 600 ) );
		setContentPane( chartPanel );
		      
	}
	
	public void addToDataset(long pathCost, String type, int generation) {
		dataset.addValue(pathCost, type, Integer.toString(generation));
	}
}
