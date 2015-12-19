package com.pr;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BasicMatch {

	double min_dis = 2000000;
	int min_x = -1;
	int min_y = -1;

	int max_width = 0;
	int max_heigh = 0;

	int center_x = 0;
	int center_y = 0;
	
	boolean isBreak=true;

	public BasicMatch() {

		try {

			BufferedImage bufferedImage = ImageIO.read(new File("test.bmp"));
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			max_heigh = height;
			max_width = width;

			System.out.println("w: " + width + " h: " + height);
			for (int i = 150; i < width/2; i++)
			{

				for (int j = 150; j < height/2; j++) { // int rgb =
					bufferedImage.getRGB(i, j);

					findMatch(i, j);
					if(isBreak)
						break;
				}
				if(isBreak)
					break;
			}

			System.out.println("x_min" + min_x + " y_min: " + min_y);

			// int rgb = bufferedImage.getRGB(0, 0); int c_r = rgb >> 16 & 0xff;
			// int c_g = rgb >> 8 & 0xff; int c_b = rgb & 0xff;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//min_x=200;
		//min_y=200;
		writeImage(min_x, min_y);
		 getCenter();
		 DlogSearch();
		// writeImage(28, 15);

	}

	private void writeImage(int x, int y) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("test.bmp"));

			BufferedImage testImage;

			int rgtets = 0xffff;
			testImage = ImageIO.read(new File("ref1.bmp"));
			int width = testImage.getWidth();
			int height = testImage.getHeight();

			System.out.println("test w: " + width + " h: " + height);
			System.out.println();
			for (int i = x; i < x + width; i++)
			{
				for (int j = y; j < y + height; j++) {
					bufferedImage.setRGB(i, j, rgtets);
					
				}
				
			}

			File file = new File("out_ref.bmp");

			ImageIO.write(bufferedImage, "bmp", file);
			System.out.println("write");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void findMatch(int x, int y) {

		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("ref1.bmp"));
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			if (x + width <= max_width && y + height <= max_heigh) {
				BufferedImage bimage = ImageIO.read(new File("test.bmp"));

				double total = 0;
				int i = 0, j = 0;
				for (i = 0; i < width; i++)
					for (j = 0; j < height; j++) {
						int rgb = bufferedImage.getRGB(i, j);
						int c_r = rgb >> 16 & 0xff;
						int c_g = rgb >> 8 & 0xff;
						int c_b = rgb & 0xff;

						int rgb_ref = bimage.getRGB(x + i, y + j);
						int r = rgb_ref >> 16 & 0xff;
						int g = rgb_ref >> 8 & 0xff;
						int b = rgb_ref & 0xff;

						double point = Math.abs(c_r - r) * Math.abs(c_r - r)
								+ Math.abs(c_g - g) * Math.abs(c_g - g)
								+ Math.abs(c_b - b) * Math.abs(c_b - b);
						point = Math.sqrt(point);

						total += point;

					}
				//System.out.println("total " + total + "  x " + x + "  y" + y);
				if (total < min_dis) {
					min_x = x;
					min_y = y;
					min_dis = total;
					System.out.println("x_min " + min_x + " y_min: " + min_y
							+ " total: " + total);
					if(min_dis==0)
						isBreak=true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getCenter() {
		/*
		 * BufferedImage bufferedImage; try { bufferedImage = ImageIO.read(new
		 * File("p_part.png")); int width = bufferedImage.getWidth(); int height
		 * = bufferedImage.getHeight(); center_x=min_x+width/2;
		 * center_y=min_y+height/2; } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		center_x = min_x;
		center_y = min_y;

		System.out.println("center x " + center_x + "y: " + center_y);

	}

	private void writeImage2D(int x, int y) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("test.bmp"));

			BufferedImage testImage;

			int rgtets = 0xffff;
			testImage = ImageIO.read(new File("ref1.bmp"));
			int width = testImage.getWidth();
			int height = testImage.getHeight();

			System.out.println("test w: " + width + " h: " + height);
			System.out.println();
			for (int i = x; i < x + width; i++)
				for (int j = y; j < y + height; j++) {
					bufferedImage.setRGB(i, j, rgtets);
				}

			File file = new File("out_ref_2.bmp");

			ImageIO.write(bufferedImage, "bmp", file);
			System.out.println("write");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private double findMatchByPoint(int x, int y) {

		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("test.bmp"));
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();

			BufferedImage bimage = ImageIO.read(new File("ref1.bmp"));

			// System.out.println(" "+max_width+"   "+max_heigh );
			if ((x + width) <= 0 && (x + width) <= bimage.getWidth()
					&& (y + height) <= bimage.getHeight() && x + width <= 0
					&& (y + height) >= 0) {

				double total = 0;
				int i = 0, j = 0;
				for (i = 0; i < width; i++)
					for (j = 0; j < height; j++) {
						int rgb = bufferedImage.getRGB(i, j);
						int c_r = rgb >> 16 & 0xff;
						int c_g = rgb >> 8 & 0xff;
						int c_b = rgb & 0xff;

						System.out.println(" " + (x + i) + "   " + (y + j));
						int rgb_ref = bimage.getRGB(x + i, y + j);
						int r = rgb_ref >> 16 & 0xff;
						int g = rgb_ref >> 8 & 0xff;
						int b = rgb_ref & 0xff;

						double point = Math.abs(c_r - r) * Math.abs(c_r - r)
								+ Math.abs(c_g - g) * Math.abs(c_g - g)
								+ Math.abs(c_b - b) * Math.abs(c_b - b);
						point = Math.sqrt(point);

						total += point;

					}

				return total;
				// System.out.println("total "+total);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	private void DlogSearch() {
		int width = max_width / 2;

		double k = get2baseLog((double) width);

		int d = (int) Math.pow(2, (int) (k) - 1);

		Point minPoint = null;
		while (d >= 1) {
			System.out.println("d initial: " + d);

			ArrayList<Point> pointList = getpoints(center_x, center_y, d);
			double totalMin = 10000000;

			for (int i = 0; i < pointList.size(); i++) {
				Point p = pointList.get(i);
				double min = findMatchByPoint(p.getX(), p.getY());
				if (min < totalMin) {
					totalMin = min;
					minPoint = p;
				}
			}
			System.out.println("new point x: " + minPoint.getX() + " y : "
					+ minPoint.getY());
			width = width / 2;
			k = get2baseLog((double) width);
			d = (int) Math.pow(2, (int) (k) - 1);
			center_x = minPoint.getX();
			center_y = minPoint.getY();
		}
		writeImage2D(minPoint.getX(), minPoint.getY());
	}

	private ArrayList<Point> getpoints(int x, int y, int d) {
		ArrayList<Point> list = new ArrayList<Point>();
		Point p = new Point(x, y);
		list.add(p);

		p = new Point(x + d, y);
		list.add(p);

		p = new Point(x - d, y);
		list.add(p);

		p = new Point(x, y + d);
		list.add(p);

		p = new Point(x, y - d);
		list.add(p);

		p = new Point(x + d, y + d);
		list.add(p);

		p = new Point(x + d, y - d);
		list.add(p);

		p = new Point(x - d, y - d);
		list.add(p);

		p = new Point(x - d, y + d);
		list.add(p);

		return list;

	}

	private double get2baseLog(double value) {
		if (value == 0)
			return 0;
		double logs = Math.log(value) / Math.log(2);
		// System.out.println("2base log"+ logs+" input: "+ value);
		return logs;
	}
}
