import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Film {
    private String title;
    private String director;
    private int year;
    private boolean watched;

    public Film(String title, String director, int year) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.watched = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}

class FilmLibrary {
    private List<Film> films;

    public FilmLibrary() {
        films = new ArrayList<>();
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public List<Film> getAllFilms() {
        return films;
    }

    public List<Film> searchFilms(String keyword) {
        List<Film> results = new ArrayList<>();
        for (Film film : films) {
            if (film.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(film);
            }
        }
        return results;
    }
}

public class FilmLibraryApp {
    private FilmLibrary filmLibrary;
    private JFrame frame;
    private JTextField searchField;
    private JTextArea resultArea;

    public FilmLibraryApp() {
        filmLibrary = new FilmLibrary();

        // Örnek filmleri ekleyelim
        filmLibrary.addFilm(new Film("The Shawshank Redemption", "Frank Darabont", 1994));
        filmLibrary.addFilm(new Film("The Godfather", "Francis Ford Coppola", 1972));
        filmLibrary.addFilm(new Film("The Dark Knight", "Christopher Nolan", 2008));

        frame = new JFrame("Film Kütüphanesi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Ara");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilms();
            }
        });
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        resultArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultArea);

        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void searchFilms() {
        String keyword = searchField.getText();
        List<Film> results = filmLibrary.searchFilms(keyword);
        displayResults(results);
    }

    private void displayResults(List<Film> results) {
        resultArea.setText("");
        if (results.isEmpty()) {
            resultArea.append("Film bulunamadı.");
        } else {
            for (Film film : results) {
                resultArea.append("Başlık: " + film.getTitle() + ", Yönetmen: " + film.getDirector() + ", Yıl: " + film.getYear() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FilmLibraryApp();
            }
        });
    }
}
