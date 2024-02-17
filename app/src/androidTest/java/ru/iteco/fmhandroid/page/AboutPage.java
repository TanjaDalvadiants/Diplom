package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.test.TestUtils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutPage {

    ViewInteraction versionTitle = onView(withId(R.id.about_version_title_text_view));
    ViewInteraction privacyTitle = onView(withId(R.id.about_privacy_policy_label_text_view));
    ViewInteraction termsOfUseTitle = onView(withId(R.id.about_terms_of_use_label_text_view));

    public void validatePageLoaded() {
        versionTitle.check(matches(isDisplayed()));
        privacyTitle.check(matches(isDisplayed()));
        termsOfUseTitle.check(matches(isDisplayed()));
    }

    public void waitUntilPageLoaded() {
        onView(isRoot()).perform(waitDisplayed(R.id.about_version_title_text_view, 6000));
    }
}
