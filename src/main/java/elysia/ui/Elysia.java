package elysia.ui;

import elysia.commands.Command;
import elysia.exceptions.ArgumentFormatException;
import elysia.exceptions.ElysiaException;
import elysia.exceptions.EmptyArgumentException;
import elysia.exceptions.EmptyTaskArgumentsException;
import elysia.exceptions.WrongArgumentException;
import elysia.parser.InputOutputHandler;

/**
 * The main class for the elysia.ui.Elysia application.
 * This class handles the main program loop, where user input is continuously accepted and processed.
 * It interacts with the user via the console and manages the flow of the application.
 */
public class Elysia {

    private final InputOutputHandler handler = new InputOutputHandler();


    /**
     * Gets the fileMessage
     * @return String of the message when file is loaded
     */
    public String getFileMessage() {
        return handler.fileMessage();
    }

    /**
     * Gets the output string by parsing the input and executing the respective commands.
     * Catches the exceptions
     * @param input The user's input as a string.
     * @return The output string to be put into DialogBox
     */
    public String getResponse(String input) {
        String response;
        try {
            Command command = handler.parseInput(input);
            response = command.execute();
        } catch (ArgumentFormatException e) {
            response = "You need to speak properly to a pretty girl...\n"
                    + "Format your " + e.getDetails() + " properly!";
        } catch (EmptyArgumentException e) {
            response = "Hmph! What do you even want me to " + e.getDetails() + "?";
        } catch (EmptyTaskArgumentsException e) {
            response = "Hmph! You don't expect me to read your mind for this " + e.getDetails() + ", do you?";
        } catch (WrongArgumentException e) {
            response = "This doesn't look like a proper " + e.getDetails() + " argument to me...";
        } catch (ElysiaException e) {
            response = "Uh oh you made a big oopsie(or maybe my maker did), I'm need to leave now...";
        }
        return response;
    }
}
