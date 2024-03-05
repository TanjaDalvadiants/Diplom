package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.test.TestUtils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    ViewInteraction newsContainer = onView(withId(R.id.container_list_news_include));
    ViewInteraction goToControlPanelButton = onView(withId(R.id.edit_news_material_button));

    public void validatePageLoaded() {
        newsContainer.check(matches(isDisplayed()));
        goToControlPanelButton.check(matches(isDisplayed()));
    }

    public void waitUntilPageLoaded() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include, 6000));
    }

    public void goToControlPanel() {
        goToControlPanelButton.perform(click());
    }

    public void checkNewsDescriptionVisible(String description) {
        onView(allOf(withText(description), isDisplayed())).check(matches(isDisplayed()));
    }

}
