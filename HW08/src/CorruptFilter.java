import java.util.Random;

/**
 * Filter that corrupts the image, biased towards the bottom left
 * Deosn't de-bayer the image, to allow for fun color blends.
 * Output will constantly change.
 *
 * @author Kai Richardson
 * @version 4.3.2019
 */
public class CorruptFilter extends PixelGridFramework implements Filter {

	@Override
	public void filter(PixelImage pi) {

		//Allow user to corrupt a bayered image, leads to fun colors

		Pixel[][] data = pi.getData();  //get image data

		for (int row = 0; row < (pi.getHeight()); row++) {
			for (int col = 0; col < (pi.getWidth()); col++) {

				setupLocalPixelGrid(data, row, col);
				Random r = new Random();

				//only corrupt given a 40% chance
				int shouldWeCorrupt = r.nextInt(5);
				if (shouldWeCorrupt < 3) {
					continue;
				}

				int rand = r.nextInt(4);

				//do random changes
				switch (rand) {
					case 1:
						trySetPixel(Center, Top);
					case 2:
						trySetPixel(Center, Left);
					case 3:
						trySetPixel(Center, TopLeft);
						trySetPixel(Center, BottomRight);
					case 4:
						trySetPixel(Center, BottomLeft);
						trySetPixel(Center, Bottom);
						trySetPixel(Center, BottomRight);
				}
			}
		}
		// reset data into the PixelImage object pi
		pi.setData(data);
	}
}
