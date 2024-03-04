package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.test.TestUtils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ControlPanelPage {
    ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));

    public void addNews() {
        addNewsButton.perform(click());
    }

    public void validatePageLoaded() {
        addNewsButton.check(matches(isDisplayed()));
    }

    public void waitUntilPageLoaded() {
        onView(isRoot()).perform(waitDisplayed(R.id.add_news_image_view, 6000));
    }

    public ViewInteraction findNewsByTitle(String title){
        return onView(withText(title));
    }
}
