package com.example.trivitup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.trivitup.databinding.ActivityMainBinding;
import com.example.trivitup.databinding.ActivitySubCategoryBinding;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class SubCategoryActivity extends AppCompatActivity {
    // Define the subcategories
    ActivitySubCategoryBinding binding;
    Map<String, String[]> subCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        binding = ActivitySubCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<CategoryModel> categories = new ArrayList<>();
        categories.add(new CategoryModel("","Maths",""));
        categories.add(new CategoryModel("","Science",""));
        categories.add(new CategoryModel("","History",""));
        categories.add(new CategoryModel("","Language",""));
        categories.add(new CategoryModel("","General Knowledge",""));
        CategoryAdapter adapter = new CategoryAdapter(this,categories);

        binding.categoryList.setLayoutManager(new GridLayoutManager(this,2));
        binding.categoryList.setAdapter(adapter);


    }

    // Method to start the quiz
    void startQuiz(String subCategory) {
        ArrayList<Question> sampleQuestions = getSampleQuestions(subCategory);

        // Start the QuizActivity with the selected subcategory
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("SUB_CATEGORY", subCategory);
        intent.putParcelableArrayListExtra("QUESTIONS", sampleQuestions);
        startActivity(intent);
    }

    // Sample questions for each subcategory
    private ArrayList<Question> getSampleQuestions(String subCategory) {
        ArrayList<Question> questions = new ArrayList<>();

        switch (subCategory) {
            //Catgories Math
            case "Algebra":
                questions.add(new Question("What is the formula for the quadratic equation?", new String[]{"A. x = (-b ± √(b² - 4ac)) / (2a)", "B. x = -b ± √(b² - 4ac) / 2a", "C. x = (b ± √(b² + 4ac)) / 2a", "D. x = -b ± √(b² + 4ac) / (2a)"}, 0));
                questions.add(new Question("Solve for x: 2x + 5 = 13", new String[]{"A. 7", "B. 4", "C. 3", "D. 9"}, 1));
                questions.add(new Question("What is the distributive property in algebra?", new String[]{"A. a(b + c) = ab + ac", "B. a(b - c) = ab - ac", "C. a(b + c) = ab - ac", "D. a(b - c) = ab + ac"}, 0));
                break;

            case "Calculus":
                questions.add(new Question("What is the derivative of x^2 with respect to x?", new String[]{"A. 2x", "B. x", "C. 2", "D. 1"}, 0));
                questions.add(new Question("Evaluate the integral: ∫(3x^2 + 2x) dx", new String[]{"A. x^3 + x^2", "B. x^3 + x", "C. x^3", "D. x^2 + x"}, 0));
                questions.add(new Question("What does the limit of a function describe?", new String[]{"A. The average rate of change", "B. The slope of the tangent line", "C. The area under the curve", "D. The function's maximum value"}, 1));
                break;

            case "Geometry":
                questions.add(new Question("What is the formula for the area of a triangle?", new String[]{"A. 0.5 * base * height", "B. length * width", "C. π * radius^2", "D. π * diameter"}, 0));
                questions.add(new Question("What is the sum of the interior angles of a triangle?", new String[]{"A. 90 degrees", "B. 180 degrees", "C. 360 degrees", "D. It varies"}, 1));
                questions.add(new Question("Which of the following is a three-dimensional shape with six rectangular faces?", new String[]{"A. Cylinder", "B. Sphere", "C. Cube", "D. Cone"}, 2));
                break;

            case "Statistics":
                questions.add(new Question("What is the mean of the following data set: 5, 8, 12, 15, 20?", new String[]{"A. 10", "B. 12", "C. 14", "D. 15"}, 2));
                questions.add(new Question("In statistics, what does 'standard deviation' measure?", new String[]{"A. Central tendency", "B. Spread of data", "C. Probability", "D. Skewness"}, 1));
                questions.add(new Question("What is the probability of rolling a sum of 7 with two six-sided dice?", new String[]{"A. 1/6", "B. 1/8", "C. 1/12", "D. 1/36"}, 0));
                break;

            case "Trigonometry":
                questions.add(new Question("What is the sine function in a right-angled triangle?", new String[]{"A. Opposite / Adjacent", "B. Adjacent / Hypotenuse", "C. Opposite / Hypotenuse", "D. Hypotenuse / Opposite"}, 0));
                questions.add(new Question("What is the value of sin(90 degrees)?", new String[]{"A. 0", "B. 1", "C. √2/2", "D. √3/2"}, 1));
                questions.add(new Question("If tan(θ) = sin(θ) / cos(θ), what does cot(θ) equal?", new String[]{"A. sin(θ)", "B. cos(θ)", "C. tan(θ)", "D. 1/tan(θ)"}, 3));
                break;

            //Catgories Science
            case "Physics":
                questions.add(new Question("What is the SI unit for force?", new String[]{"A. Newton", "B. Watt", "C. Joule", "D. Pascal"}, 0));
                questions.add(new Question("According to Newton's third law, for every action, there is an equal and opposite...", new String[]{"A. Reaction", "B. Force", "C. Momentum", "D. Acceleration"}, 0));
                questions.add(new Question("What is the formula for kinetic energy?", new String[]{"A. E = mc^2", "B. E = 0.5 * mv^2", "C. E = mgh", "D. E = Fd"}, 1));
                break;

            case "Chemistry":
                questions.add(new Question("What is the chemical symbol for gold?", new String[]{"A. Au", "B. Ag", "C. Fe", "D. Pb"}, 0));
                questions.add(new Question("What is the pH scale used to measure?", new String[]{"A. Temperature", "B. Acidity or alkalinity", "C. Pressure", "D. Density"}, 1));
                questions.add(new Question("Which gas makes up the majority of Earth's atmosphere?", new String[]{"A. Oxygen", "B. Nitrogen", "C. Carbon dioxide", "D. Hydrogen"}, 1));
                break;

            case "Biology":
                questions.add(new Question("What is the powerhouse of the cell?", new String[]{"A. Nucleus", "B. Ribosome", "C. Mitochondria", "D. Endoplasmic reticulum"}, 2));
                questions.add(new Question("Which organelle is responsible for photosynthesis in plant cells?", new String[]{"A. Chloroplast", "B. Golgi apparatus", "C. Lysosome", "D. Vacuole"}, 0));
                questions.add(new Question("What is the largest organ in the human body?", new String[]{"A. Heart", "B. Liver", "C. Skin", "D. Brain"}, 2));
                break;

            case "Astronomy":
                questions.add(new Question("Which planet is known as the 'Red Planet'?", new String[]{"A. Venus", "B. Mars", "C. Jupiter", "D. Saturn"}, 1));
                questions.add(new Question("What is the name of the galaxy that contains our solar system?", new String[]{"A. Milky Way", "B. Andromeda", "C. Triangulum", "D. Whirlpool"}, 0));
                questions.add(new Question("What is a light-year?", new String[]{"A. A unit of time", "B. A unit of distance", "C. A unit of luminosity", "D. A unit of brightness"}, 1));
                break;

            case "Geology":
                questions.add(new Question("What type of rock is formed from cooled lava?", new String[]{"A. Sedimentary", "B. Metamorphic", "C. Igneous", "D. Fossilized"}, 2));
                questions.add(new Question("Which of the following is not a type of plate boundary?", new String[]{"A. Convergent", "B. Divergent", "C. Transverse", "D. Colliding"}, 3));
                questions.add(new Question("What is the hardest natural mineral?", new String[]{"A. Quartz", "B. Diamond", "C. Topaz", "D. Feldspar"}, 1));
                break;

            //Catgories General Knowledge
            case "History":
                questions.add(new Question("Who was the first President of the United States?", new String[]{"A. Thomas Jefferson", "B. George Washington", "C. Abraham Lincoln", "D. John Adams"}, 1));
                questions.add(new Question("In which year did World War II end?", new String[]{"A. 1943", "B. 1945", "C. 1947", "D. 1950"}, 1));
                questions.add(new Question("What ancient civilization built the pyramids at Giza?", new String[]{"A. Greek", "B. Roman", "C. Egyptian", "D. Mesopotamian"}, 2));
                break;

            case "Geography":
                questions.add(new Question("What is the capital city of France?", new String[]{"A. Berlin", "B. Madrid", "C. Paris", "D. Rome"}, 2));
                questions.add(new Question("Which continent is known as the 'Land Down Under'?", new String[]{"A. Europe", "B. Africa", "C. Australia", "D. Asia"}, 2));
                questions.add(new Question("What is the longest river in the world?", new String[]{"A. Nile", "B. Amazon", "C. Yangtze", "D. Mississippi"}, 1));
                break;

            case "Art":
                questions.add(new Question("Who painted the Mona Lisa?", new String[]{"A. Pablo Picasso", "B. Leonardo da Vinci", "C. Vincent van Gogh", "D. Michelangelo"}, 1));
                questions.add(new Question("Which art movement is characterized by its use of geometric shapes and primary colors?", new String[]{"A. Impressionism", "B. Cubism", "C. Surrealism", "D. Abstract Expressionism"}, 1));
                questions.add(new Question("What type of art involves arranging and pasting together pieces of paper or photographs?", new String[]{"A. Sculpture", "B. Collage", "C. Fresco", "D. Mosaic"}, 1));
                break;

            case "Literature":
                questions.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"A. William Shakespeare", "B. Jane Austen", "C. Charles Dickens", "D. Mark Twain"}, 0));
                questions.add(new Question("In which novel would you find the character Atticus Finch?", new String[]{"A. To Kill a Mockingbird", "B. Pride and Prejudice", "C. 1984", "D. The Great Gatsby"}, 0));
                questions.add(new Question("Which epic poem tells the story of the Trojan War?", new String[]{"A. The Iliad", "B. The Odyssey", "C. Beowulf", "D. The Aeneid"}, 0));
                break;

            case "Sports":
                questions.add(new Question("In which sport would you perform a slam dunk?", new String[]{"A. Tennis", "B. Basketball", "C. Soccer", "D. Golf"}, 1));
                questions.add(new Question("Which country won the FIFA World Cup in 2018?", new String[]{"A. Brazil", "B. Germany", "C. France", "D. Argentina"}, 2));
                questions.add(new Question("Who is often referred to as 'The Greatest' in boxing?", new String[]{"A. Mike Tyson", "B. Floyd Mayweather Jr.", "C. Muhammad Ali", "D. Manny Pacquiao"}, 2));
                break;

            //Catgories Random
            case "Movies":
                questions.add(new Question("Who directed the movie 'Inception'?", new String[]{"A. Christopher Nolan", "B. Quentin Tarantino", "C. Steven Spielberg", "D. Martin Scorsese"}, 0));
                questions.add(new Question("Which actor played the character Tony Stark in the Marvel Cinematic Universe?", new String[]{"A. Chris Evans", "B. Chris Hemsworth", "C. Robert Downey Jr.", "D. Mark Ruffalo"}, 2));
                questions.add(new Question("What film won the Academy Award for Best Picture in 2020?", new String[]{"A. Parasite", "B. 1917", "C. Joker", "D. Once Upon a Time in Hollywood"}, 0));
                break;

            case "Music":
                questions.add(new Question("Who is known as the 'Queen of Pop'?", new String[]{"A. Madonna", "B. Beyoncé", "C. Lady Gaga", "D. Rihanna"}, 0));
                questions.add(new Question("Which legendary rock band performed the song 'Stairway to Heaven'?", new String[]{"A. The Rolling Stones", "B. Led Zeppelin", "C. The Beatles", "D. Queen"}, 1));
                questions.add(new Question("Who won the Grammy Award for Album of the Year in 2021?", new String[]{"A. Taylor Swift", "B. Beyoncé", "C. Billie Eilish", "D. Adele"}, 2));
                break;

            case "Pop Culture":
                questions.add(new Question("What reality TV show features celebrities living together in a house?", new String[]{"A. The Bachelor", "B. Survivor", "C. Big Brother", "D. The Amazing Race"}, 2));
                questions.add(new Question("Which social media platform is known for its short video content?", new String[]{"A. Instagram", "B. Snapchat", "C. TikTok", "D. Twitter"}, 2));
                questions.add(new Question("Who is known for the phrase 'Keeping Up with the Kardashians'?", new String[]{"A. Kim Kardashian", "B. Kylie Jenner", "C. Kendall Jenner", "D. Kris Jenner"}, 3));
                break;

            case "Technology":
                questions.add(new Question("Who co-founded Microsoft along with Bill Gates?", new String[]{"A. Steve Jobs", "B. Jeff Bezos", "C. Elon Musk", "D. Paul Allen"}, 3));
                questions.add(new Question("Which company developed the Android operating system?", new String[]{"A. Apple", "B. Microsoft", "C. Google", "D. Samsung"}, 2));
                questions.add(new Question("What does the acronym 'URL' stand for?", new String[]{"A. Universal Resource Locator", "B. Uniform Resource Locator", "C. Universal Reference Locator", "D. Uniform Reference Locator"}, 1));
                break;

            case "Food":
                questions.add(new Question("What is the main ingredient in guacamole?", new String[]{"A. Avocado", "B. Tomato", "C. Onion", "D. Garlic"}, 0));
                questions.add(new Question("Which cuisine is known for dishes like sushi and sashimi?", new String[]{"A. Italian", "B. Japanese", "C. Mexican", "D. Indian"}, 1));
                questions.add(new Question("What is the national dish of Thailand?", new String[]{"A. Pho", "B. Pad Thai", "C. Sushi", "D. Kimchi"}, 1));
                break;

            default:
                // Default case or handle unknown subcategory
                break;
        }

        return questions;
    }
}