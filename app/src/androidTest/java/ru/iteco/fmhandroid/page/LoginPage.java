package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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

public class LoginPage {

    ViewInteraction loginInputText = onView(
            allOf(
                    isDescendantOfA(withId(R.id.login_text_input_layout)), // Это ID вашего TextInputLayout
                    isAssignableFrom(EditText.class) // Указывает, что мы ищем EditText
            )
    );

    ViewInteraction passwordInputText = onView(
            allOf(
                    isDescendantOfA(withId(R.id.password_text_input_layout)), // Это ID вашего TextInputLayout
                    isAssignableFrom(EditText.class) // Указывает, что мы ищем EditText
            )
    );

    ViewInteraction signInButton = onView(withId(R.id.enter_button));

    public void validatePageLoaded() {
        loginInputText.check(matches(isDisplayed()));
        passwordInputText.check(matches(isDisplayed()));
        signInButton.check(matches(isDisplayed()));
    }

    public void waitUntilPageLoaded() {
        onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 10_000));
    }

    public void typeLogin(String login) {
        loginInputText.perform(replaceText(login), closeSoftKeyboard());
    }

    public void typePassword(String password) {
        passwordInputText.perform(replaceText(password), closeSoftKeyboard());
    }

    public void signIn() {
        signInButton.perform(click());
    }

    public void waitUntilErrorMessageLoaded() {
        //У Espresso нет возможности взаимодействовать с системным pop up
        try {
            Thread.sleep(1_000);
        } catch (Exception e){
        }
    }

}

