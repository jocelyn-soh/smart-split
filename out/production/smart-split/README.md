# smart-split

# Challenge 2
![Picture of ERD.](./RMS_ERD.png)

## Explanation of relationships
1. A customer can make many bookings(e.g. different date, same date but more rooms, etc,) but a booking can only belong 
to one customer.
2. One booking requires one payment and a payment can only belong to one booking. 
3. One booking can be assigned to many rooms (if there are >1 room booked) but one room can only be assigned to one 
customer within the same time period. 

## Assumptions made
1. Rooms of the same type vary only in size, themed decor, bathroom amenities and furnishings.
2. Payment ID and booking ID will only be issued when the payment is successful.

## Constraints
The ERD is unable to capture certain domain/value constraints such as the fact that the email and phone number of the
customer must be unique. Another such example of the domain constraint that cannot be captured is the fact that the room
types are only Standard, Deluxe and Suite. 
