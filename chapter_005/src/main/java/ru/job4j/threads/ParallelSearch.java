package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.nio.file.FileVisitResult.CONTINUE;

@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;
    volatile boolean finish = false;

    @GuardedBy("this")
    private final Queue<String> files = new LinkedList<>();

    @GuardedBy("this")
    private final Queue<String> paths = new LinkedList<>();


    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public void init() {

        SimpleFileVisitor fileVisitor = new SimpleFileVisitor() {
            @Override
            public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
                if (attrs.isRegularFile()) {
                    int index = file.toString().lastIndexOf('.');
                    for (String ext : exts) {
                        if (file.toString().substring(index).equals(ext)) {
                            System.out.println("Найденные фаилы " + file.toString());
                            files.offer(file.toString());
                        }
                    }
                }
                return CONTINUE;
            }
        };

        Thread search = new Thread() {

            @Override
            public void run() {
                try {
                    Files.walkFileTree(Paths.get(root), fileVisitor);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish = true;
            }
        };
        Thread read = new Thread() {
            @Override
            public void run() {
                while (!finish || !files.isEmpty()) {
                    if (!files.isEmpty()) {
                        String path = files.poll();
                        try {
                            List<String> content = Files.readAllLines(Paths.get(path));
                            for (String st : content) {
                                if (st.contains(text)) {
                                    paths.offer(path);
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        search.start();
        read.start();
    }

    synchronized Queue<String>  result() {
        for (String st : paths) {
            System.out.println("Текст найден в " + st);
        }
        return this.paths;
    }
}