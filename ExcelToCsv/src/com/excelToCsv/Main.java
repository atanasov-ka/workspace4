package com.excelToCsv;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import jxl.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		if (args.length != 2)
		{
			System.err.println("Arguments required: path/to/src.xls path/to/outdir");
			return;
		}
		
		try {
			// PATH TO INPUT XSL
			String pathToXls = args[0];;
			
			// PATH TO OUTPUT CSV
			String pathToCsv = args[1];
			
			if (!Files.exists(Paths.get(pathToCsv)))
			{
				Files.createDirectory(Paths.get(pathToCsv));
			}
			
			// Excel document to be imported
			String filename = pathToXls;
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(new File(filename), ws);

			// Gets the sheets from workbook
			for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++) {
				Sheet s = w.getSheet(sheet);
				
				String pathToCsvConcrete = pathToCsv + "\\" + sheet + ".csv";
				// File to store data in form of CSV
				File f = new File(pathToCsvConcrete);

				OutputStream os = (OutputStream) new FileOutputStream(f);
				String encoding = "UTF8";
				OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
				BufferedWriter bw = new BufferedWriter(osw);

				Cell[] row = null;

				// Gets the cells from sheet
				for (int i = 0; i < s.getRows(); i++) {
					row = s.getRow(i);

					if (row.length > 0) {
						bw.write("\"" + row[0].getContents() + "\"");
						for (int j = 1; j < row.length; j++) {
							bw.write(',');
							bw.write("\"" + row[j].getContents() + "\"");
						}
					}
					bw.newLine();
				}
				bw.flush();
				bw.close();
				System.out.println("" + sheet + "/" + w.getNumberOfSheets() + " converted successiful! " + pathToCsvConcrete);
			}
			
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println(e.toString());
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		System.out.println("Done!");
	}

}
