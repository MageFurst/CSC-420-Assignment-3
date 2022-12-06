# CSC-420-Assignment-3
This homework builds on HW1 by adding concurrency and a progress bar.

Build a Java Swing application that allows users to select a European country's name out of a list, and then displays its flag in another panel. Make sure the layout manager is good (I recommend MigLayout) and that you use good UI design principles when placing your components. A button that occupies half the screen will not suffice this time.

This time I'd like you to use at least 100 different images. Load these images in the background (not on the EDT) by using SwingWorkers. While you are loading them, display a splash screen with a progress bar to the user (this should happen on the EDT, of course). The progress bar should gradually get filled as you load more images.

You should only display the full GUI after you have pre-loaded all images.

Submit your sources and resources (all your .java files in the correct directory structure, as well as the images you use in the proper place within the directory structure) to the Blackboard dropbox before the deadline.

I'll want you to demonstrate how your homework works after you submit it, too.

All Swing homeworks are individual. Group work is not permitted.
