package br.com.deleaolucas.desafiodimed.taxistop.util;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileProcessor {

    public String fileReader(final String filename, final int initLine, final int endLine) {

        try (final InputStream inputStream = new FileInputStream(filename);
             final Scanner scanner = new Scanner(inputStream)) {

            final StringBuilder content = new StringBuilder(100);
            int linesRead = 0;
            int linesToRead = endLine - initLine;
            while (scanner.hasNextLine()) {
                linesRead++;
                final String line = scanner.nextLine();

                if (linesRead >= initLine) {
                    if (linesToRead-- == 0) {
                        break;
                    }
                    content.append(line);
                    content.append("\n");
                }
            }

            return content.toString();

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Was not possible to access the file", ex);
        }
    }

    public void appendToFile(final String filename, final String line) {
        final boolean append = true;

        try (final FileWriter fileWriter = new FileWriter(filename, append)) {

            fileWriter.append(line);
            fileWriter.append('\n');

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Was not possible to access the file", ex);
        }
    }

    public List<String> getLinesThatHasString(final String filename, final String string) {

        try (final InputStream inputStream = new FileInputStream(filename);
             final Scanner scanner = new Scanner(inputStream)) {

            final List<String> list = new ArrayList<>(5);

            while (scanner.hasNextLine()) {

                final String line = scanner.nextLine();
                if (line.contains(string)) {
                    list.add(line);
                }
            }

            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Was not possible to access the file", ex);
        }
    }

}
