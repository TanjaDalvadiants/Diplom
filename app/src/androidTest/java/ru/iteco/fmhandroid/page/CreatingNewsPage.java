package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.test.TestUtils.waitDisplayed;

import android.widget.EditText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CreatingNewsPage {
    ViewInteraction addNewsTitle = onView(withId(R.id.container_custom_app_bar_include_on_fragment_create_edit_news));
    ViewInteraction categoryInputText = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    ViewInteraction titleInputText = onView(withId(R.id.news_item_title_text_input_edit_text));
    ViewInteraction dateInputText = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    ViewInteraction timeInputText = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    ViewInteraction descriptionInputText = onView(
            allOf(
                    isDescendantOfA(withId(R.id.news_item_description_text_input_layout)), // Это ID вашего TextInputLayout
                    isAssignableFrom(EditText.class) // Указывает, что мы ищем EditText
            )
    );
     ViewInteraction addNewsButton = onView(withId(R.id.save_button));



    public void validatePageLoaded() {
        addNewsTitle.check(matches(isDisplayed()));
        categoryInputText.check(matches(isDisplayed()));
        titleInputText.check(matches(isDisplayed()));
        dateInputText.check(matches(isDisplayed()));
        timeInputText.check(matches(isDisplayed()));
        descriptionInputText.check(matches(isDisplayed()));
        addNewsButton.check(matches(isDisplayed()));
    }

    public void waitUntilPageLoaded() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_custom_app_bar_include_on_fragment_create_edit_news, 6000));
    }

    public void typeCategory(String category) {
        categoryInputText.perform(replaceText(category), closeSoftKeyboard());
    }

    public void typeTitle(String title) {
        titleInputText.perform(replaceText(title), closeSoftKeyboard());
    }

    public void typeDate(String date) {
        dateInputText.perform(replaceText(date), closeSoftKeyboard());
    }

    public void typeTime() {
        timeInputText.perform(click());
        onView(isRoot()).perform(waitDisplayed(android.R.id.button1, 6000));
        onView(withId(android.R.id.button1)) // ID кнопки "OK" в стандартном диалоге
                .inRoot(isDialog()) // Убедитесь, что мы ищем в корне диалогового окна
                .check(matches(isDisplayed()))
                .perform(click());
        onView(isRoot()).perform(waitDisplayed(R.id.news_item_publish_time_text_input_edit_text, 6000));
    }

    public void typeDescription(String description) {
        descriptionInputText.perform(replaceText(description), closeSoftKeyboard());
    }

    public void addNews() {
        addNewsButton.perform(click());
    }

}
