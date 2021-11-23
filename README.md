# Branki Readme
Branki (a portmanteau Brant and Anki, the former being my name and latter being a popular flashcard app) is a flashcard
app. As a flashcard app, Branki will provide the following functionality:

* Allow users to create and maintain decks of flashcards
* Allow users to hold study sessions with these decks

I suspect I will be the only person to use this app, although anyone who uses flashcards to study could use it. I am
interested in this project because I've found flashcards to be an effective study method.

## User Stories

Here is a list of user stories this project will realize:
* As a user, I want to be able to create a deck of flashcards
* As a user, I want to be able to view a deck of flashcards (names, size, and difficulty)
* As a user, I want to be able to rename a deck of flashcards
* As a user, I want to be able to delete a deck of flashcards
* As a user, I want to be able to add a card to a deck
* As a user, I want to be able to view the cards in a deck (questions and answers)
* As a user, I want to be able to modify a card in a deck (question and answer)
* As a user, I want to be able to delete a card from a deck
* As a user, I want to be able to study a deck (go through cards and log difficulty)
* As a user, I want to be able to save all of my decks to a file
* As a user, I want to be able to load all of my decks from a file
* As a user, when I quit I want to be alerted to and be able to save unsaved changes

## Phase 4: Task 2

Here is a representative sample of events logged by event logging:

Mon Nov 22 00:37:23 PST 2021
Deck name changed from Times Tables to Easy Times Tables

Mon Nov 22 00:37:36 PST 2021
Card with question What is 0 * 0? added to deck with name Easy Times Tables

Mon Nov 22 00:37:47 PST 2021
Card with question What is 1 * 1? added to deck with name Easy Times Tables

Mon Nov 22 00:37:57 PST 2021
Card question changed from What is 1 * 1? to What is 1 * 1?

Mon Nov 22 00:37:57 PST 2021
Card answer changed from 2 to 1

Mon Nov 22 00:38:10 PST 2021
Card with question What is 2 * 2? added to deck with name Easy Times Tables

Mon Nov 22 00:38:16 PST 2021
Card with question What is 2 * 2? removed from deck with name Easy Times Tables

Mon Nov 22 00:38:42 PST 2021
Result with difficulty 1 added to card with question What is 0 * 0?

Mon Nov 22 00:38:44 PST 2021
Result with difficulty 1 added to card with question What is 1 * 1?

## Phase 4: Task 3

If I had more time to work on this project, I would consider refactoring it as follows:
* The model classes seem to be hierarchical. So implementing the composite pattern seems advisable.
* The EditCardWindow class has an association to the Deck class that could be eliminated if the association between
Deck and Card was bidirectional. So implementing this bidirectional relationship seems advisable.
* The way the Selector type hierarchy was implemented requires downcasting of Selectable instances in subtypes of
Selector. This suggests that a different approach may be advisable.
* Almost all the differences between non-abstract types in the Selector type hierarchy concern an action listener.
Passing action listeners as arguments may allow much of this hierarchy to be eliminated. So passing action listeners
as arguments seems advisable (if it is possible).
* The classes in the ui.gui.views.windows package appear to share common code. This suggests implementing a
type hierarchy is advisable.