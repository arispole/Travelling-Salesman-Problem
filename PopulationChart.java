import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class PopulationChart extends ApplicationFrame {
	
	protected DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

	public PopulationChart(String applicationTitle, String chartTitle) {
		
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, 
				"Generations",
				"Number of Individuals",
				dataset,
				PlotOrientation.VERTICAL,
				true,true,false);
		         
		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 1500 , 600 ) );
		setContentPane( chartPanel );
		
	}
	
	public void addToDataset(double survivors, String type, int generation) {
		dataset.addValue(survivors, type, Integer.toString(generation));
	}

}