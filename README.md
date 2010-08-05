# Reminder

Sends you reminder emails for birthdays or other yearly occurences.

## Details

The app has a list of 'events', which consists of:

* A name
* A date and month (when in the year it occurs)
* A list of the number of days in which you will be notified in advance (0 = same day)

The app runs continuously on a server, and will send a notification of any event closing in each midnight.

You specify how many days in advance you want to be notified, and you can be notified from 0 to N times for each event.

The notification comes in form of an email. You will need to have SMTP access to an email server which will be used to send the email.

## Future development

I hope to have it listen on a port which supports basic commands, like "next 10" to view the next 10 upcoming events.

## Warning

1. This application is pretty pointless, there are better ways to be reminded of such events. Online calendar synchronization, etc.
2. It is also written in bad Scala style, because I wrote it to *learn* Scala. So expect both bugs and bad design.

