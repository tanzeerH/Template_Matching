package com.pr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class HierarchicalSearch {

	private ArrayList<ArrayList<Integer>> rgbList = new ArrayList<ArrayList<Integer>>();

	public HierarchicalSearch() {

		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("p.png"));
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			init(height);

			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++) {
					rgbList.get(i).add(getAverage(i, j));
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void init(int size) {
		
		for(int i=0;i<size;i++)
		{
			ArrayList<Integer> list=new ArrayList<Integer>();
			rgbList.add(list);
		}
	}

	private int getAverage(int x, int y) {
		BufferedImage bufferedImage;
		int rgb = 0;
		try {
			bufferedImage = ImageIO.read(new File("p.png"));
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();

			int i, j;
			i = x + 1;
			j = y;
			rgb += bufferedImage.getRGB(i, j);

			i = x;
			j = y + 1;
			rgb += bufferedImage.getRGB(i, j);

			i = x + 1;
			j = y + 1;
			rgb += bufferedImage.getRGB(i, j);

			i = x + 1;
			j = y - 1;
			rgb += bufferedImage.getRGB(i, j);

			i = x - 1;
			j = y - 1;
			rgb += bufferedImage.getRGB(i, j);

			i = x - 1;
			j = y + 1;
			rgb += bufferedImage.getRGB(i, j);

			i = x;
			j = y - 1;
			rgb += bufferedImage.getRGB(i, j);

			i = x;
			j = y + 1;
			rgb += bufferedImage.getRGB(i, j);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rgb/8;

	}
}
