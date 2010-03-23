# Reminder

Sends you reminder emails for birthdays or other yearly occurences.

## Details

It consists of

1. A *server*
2. A *GUI client*

The server runs constantly on some remote machine. It will have a list of events/persons, and reminder rules for each of them (for being reminded a week before the event, a day before, both, etc.). Each night, at 00:00, the list is checked, and if a rule is activated, then an email is sent to your email address.

The system has two open ports for management/communication:

1. One for the GUI client, which can read and change system data (events, rules, email addresses, etc.)
2. One you may connect to via telnet and enter simple commands, for example "next 10" to view the next 10 events and their dates, for easy accessibility (e.g. through shell scripts).

*There is no authentication*, because in my case the server is behind a NAT and the ports are not open externally, so there is no need for it.

*I'm not sure how the email system will work. At the moment, I've only managed to get it working by sending from my own GMail account.*

The server runs on a constantly running machine, and is managed from the GUI client.

## Warning
1. As of this writing, I haven't actually written anything. So the following is the PLANS I have for the application.
2. The application is written in Scala, which I don't have much experience with. So expect both bugs and bad design. (I expect it will be a good learning experience for me, though).
3. This is another of those "reinventing the wheel"-apps I like to write because I'm not satisified with the existing solutions out there. *If* I get a smartphone with google calendar synchronization, I will probably use that instead.
