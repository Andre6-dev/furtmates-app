-- liquibase formatted sql
-- changeset devandre:add_users_data
-- Insert 10 users with different roles and genders
INSERT INTO users (public_id, first_name, last_name, username, email, password_hash, phone_number, address,
                   document_number, role_id, avatar_url, age, genre, is_enabled, is_adopter, bio)
VALUES ('f8d1a1fa-d17f-4c96-bd10-0123456789ab', 'John', 'Doe', 'johndoe', 'johndoe@example.com', 'hashed_password_1',
        '123-456-7890', '123 Main St, Springfield', 'A123456789', 1, 'https://example.com/avatar1.jpg', 35, 'MALE',
        TRUE, FALSE, 'Software Engineer with a passion for animals.'),
       ('a5c1b69f-b5fa-4d2f-bb59-123456789abc', 'Alice', 'Smith', 'alicesmith', 'alicesmith@example.com',
        'hashed_password_2', '234-567-8901', '456 Oak Ave, Springfield', 'B987654321', 2,
        'https://example.com/avatar2.jpg', 28, 'FEMALE', TRUE, FALSE, 'Software Engineer with a passion for animals.'),
       ('e48c3db7-ef32-4c63-9d7d-123456789def', 'Mike', 'Johnson', 'mikejohnson', 'mikejohnson@example.com',
        'hashed_password_3', '345-678-9012', '789 Pine Dr, Springfield', 'C112233445', 3,
        'https://example.com/avatar3.jpg', 42, 'MALE', TRUE, TRUE, 'Software Engineer with a passion for animals.'),
       ('8a7f0535-ada0-44f7-bfbf-1234567890ab', 'Sophia', 'Brown', 'sophiabrown', 'sophiabrown@example.com',
        'hashed_password_4', '456-789-0123', '123 Maple Rd, Springfield', 'D223344556', 2,
        'https://example.com/avatar4.jpg', 30, 'FEMALE', TRUE, TRUE, 'Software Engineer with a passion for animals.'),
       ('b9c60a6b-d2ab-4cd9-bf23-1234567890cd', 'James', 'Miller', 'jamesmiller', 'jamesmiller@example.com',
        'hashed_password_5', '567-890-1234', '345 Cedar Blvd, Springfield', 'E445566778', 3,
        'https://example.com/avatar5.jpg', 25, 'MALE', TRUE, TRUE, 'Software Engineer with a passion for animals.'),
       ('79d03cd5-fd1d-4cc7-bbb5-123456789012', 'Olivia', 'Davis', 'oliviadavis', 'oliviadavis@example.com',
        'hashed_password_6', '678-901-2345', '456 Birch Ln, Springfield', 'F667788990', 1,
        'https://example.com/avatar6.jpg', 33, 'FEMALE', TRUE, FALSE, 'Software Engineer with a passion for animals.'),
       ('21b859cf-06fa-4cd4-8888-1234567890ef', 'Robert', 'Martinez', 'robertmartinez', 'robertmartinez@example.com',
        'hashed_password_7', '789-012-3456', '567 Fir Ave, Springfield', 'G223344889', 1,
        'https://example.com/avatar7.jpg', 38, 'MALE', TRUE, FALSE, 'Software Engineer with a passion for animals.'),
       ('2b9355a1-8ed5-4e24-97a1-123456789012', 'Emily', 'Garcia', 'emilygarcia', 'emilygarcia@example.com',
        'hashed_password_8', '890-123-4567', '678 Elm St, Springfield', 'H889900001', 3,
        'https://example.com/avatar8.jpg', 22, 'FEMALE', TRUE, TRUE, 'Software Engineer with a passion for animals.'),
       ('c1ad1e92-3e7b-47e0-9f92-123456789345', 'David', 'Wilson', 'davidwilson', 'davidwilson@example.com',
        'hashed_password_9', '901-234-5678', '789 Redwood Rd, Springfield', 'I998877665', 2,
        'https://example.com/avatar9.jpg', 45, 'MALE', TRUE, FALSE, 'Software Engineer with a passion for animals.'),
       ('f60b623f-e9c5-4bfc-bb15-123456789234', 'Chloe', 'Taylor', 'chloetaylor', 'chloetaylor@example.com',
        'hashed_password_10', '012-345-6789', '890 Willow Dr, Springfield', 'J667788990', 2,
        'https://example.com/avatar10.jpg', 29, 'FEMALE', TRUE, TRUE, 'Software Engineer with a passion for animals.');

-- liquibase formatted sql
-- changeset devandre:add_species_data
INSERT INTO species (name, updated_by)
VALUES ('Dog', 1),
       ('Cat', 1),
       ('Bird', 1),
       ('Rabbit', 1),
       ('Reptile', 1);

-- liquibase formatted sql
-- changeset devandre:add_breeds_data
INSERT INTO breeds (name, species_id, updated_by)
VALUES ('Labrador Retriever', 1, 1),
       ('German Shepherd', 1, 1),
       ('Golden Retriever', 1, 1),
       ('Bulldog', 1, 1),
       ('Beagle', 1, 1);

-- Insert breeds data for Cat
INSERT INTO breeds (name, species_id, updated_by)
VALUES ('Siamese', 2, 1),
       ('Persian', 2, 1),
       ('Maine Coon', 2, 1),
       ('Bengal', 2, 1),
       ('Sphynx', 2, 1);

-- Insert breeds data for Bird
INSERT INTO breeds (name, species_id, updated_by)
VALUES ('Parrot', 3, 1),
       ('Canary', 3, 1),
       ('Cockatiel', 3, 1),
       ('Finch', 3, 1),
       ('Lovebird', 3, 1);

-- Insert breeds data for Rabbit
INSERT INTO breeds (name, species_id, updated_by)
VALUES ('Holland Lop', 4, 1),
       ('Mini Rex', 4, 1),
       ('English Angora', 4, 1),
       ('Mini Lop', 4, 1),
       ('Himalayan', 4, 1);

-- Insert breeds data for Reptile
INSERT INTO breeds (name, species_id, updated_by)
VALUES ('Bearded Dragon', 5, 1),
       ('Leopard Gecko', 5, 1),
       ('Ball Python', 5, 1),
       ('Chameleon', 5, 1),
       ('Iguana', 5, 1);

-- liquibase formatted sql
-- changeset devandre:add_shelters_data
INSERT INTO shelters (name, phone_number, address, email, updated_by)
VALUES ('Happy Tails Shelter', '+1234567890', '123 Dogwood St, Springfield, IL', 'contact@happytails.com', 1),
       ('Paws and Claws Shelter', '+1987654321', '456 Oak Rd, Riverside, CA', 'info@pawsandclaws.org', 1),
       ('The Cat Haven', '+1122334455', '789 Birch Blvd, Dallas, TX', 'adopt@thecathaven.org', 1),
       ('Animal Rescue Center', '+1444332211', '234 Pine St, New York, NY', 'info@animalrescuecenter.org', 1),
       ('The Rabbit Refuge', '+1555443322', '567 Maple Ave, Seattle, WA', 'hello@rabbitrefuge.org', 1),
       ('Reptile Rescue Sanctuary', '+1666777888', '101 Elm Rd, Miami, FL', 'contact@reptilerescuesanctuary.com', 1);

-- liquibase formatted sql
-- changeset devandre:add_pets_data
INSERT INTO pets (name, species_id, breed_id, age, genre, weight, description, size, color, health_status,
                  characteristics, adoption_status, shelter_id, arrival_date, good_with, is_spayed, updated_by)
VALUES ('Buddy', 1, 1, 'ADULT', 'MALE', 30.00, 'Friendly, energetic, loves to play fetch.', 'Medium', 'Golden',
        'Healthy',
        'Loves children and other dogs', 'AVAILABLE', 1, '2024-09-15', 'Children, Dogs', TRUE, 1), -- Dog - Labrador
       ('Mittens', 2, 3, 'BABY', 'FEMALE', 4.50, 'Cute, loves to cuddle and play with toys.', 'Small', 'Grey',
        'Healthy',
        'Prefers being the only pet', 'ADOPTED', 3, '2024-08-20', 'None', TRUE, 1),                -- Cat - Maine Coon
       ('Charlie', 1, 4, 'ADULT', 'MALE', 25.00, 'Playful, loves being around people.', 'Medium', 'Brown', 'Healthy',
        'Good with children, loves to go for walks', 'AVAILABLE', 2, '2024-10-05', 'Children, Dogs', TRUE,
        2),                                                                                        -- Dog - Bulldog
       ('Fluffy', 2, 2, 'SENIOR', 'FEMALE', 6.00, 'Quiet and calm, enjoys relaxing by windows.', 'Small', 'White',
        'Healthy',
        'Good with other cats', 'AVAILABLE', 2, '2024-09-10', 'Cats', TRUE, 3),                    -- Cat - Persian
       ('Rocky', 5, 5, 'ADULT', 'MALE', 20.00, 'Independent, prefers a calm environment.', 'Medium', 'Green', 'Healthy',
        'Likes sunbathing, good with older children', 'ADOPTED', 4, '2024-06-15', 'Children', TRUE,
        1);
-- Reptile - Bearded Dragon


-- liquibase formatted sql
-- changeset devandre:add_pet_images_data
INSERT INTO pet_images (pet_id, image_url)
VALUES (1, 'https://example.com/images/buddy_1.jpg'),   -- Buddy (Dog)
       (1, 'https://example.com/images/buddy_2.jpg'),   -- Buddy (Dog)
       (2, 'https://example.com/images/mittens_1.jpg'), -- Mittens (Cat)
       (3, 'https://example.com/images/charlie_1.jpg'), -- Charlie (Dog)
       (4, 'https://example.com/images/fluffy_1.jpg');
-- Fluffy (Cat)

-- liquibase formatted sql
-- changeset devandre:add_adoptions_data
INSERT INTO adoptions (pet_id, adopter_id, adoption_date, status)
VALUES (2, 1, '2024-09-18', 'ADOPTED'),   -- Mittens (Cat) adopted by User 1
       (5, 2, '2024-10-01', 'ADOPTED'),   -- Rocky (Reptile) adopted by User 2
       (1, 3, '2024-10-05', 'AVAILABLE'), -- Buddy (Dog) adoption in progress by User 3
       (4, 4, '2024-09-25', 'ADOPTED'),   -- Fluffy (Cat) adopted by User 4
       (3, 5, '2024-10-10', 'AVAILABLE');
-- Charlie (Dog) adoption in progress by User 5

-- liquibase formatted sql
-- changeset devandre:add_users_favorites_data
INSERT INTO users_favorites (user_id, pet_id)
VALUES (1, 1), -- User 1 marked Buddy (Dog) as a favorite
       (2, 2), -- User 2 marked Mittens (Cat) as a favorite
       (3, 3), -- User 3 marked Charlie (Dog) as a favorite
       (4, 4), -- User 4 marked Fluffy (Cat) as a favorite
       (5, 5);
-- User 5 marked Rocky (Reptile) as a favorite

-- liquibase formatted sql
-- changeset devandre:add_appointments_data
INSERT INTO appointments (user_id, pet_id, appointment_date, status, created_by)
VALUES (1, 1, '2024-10-12 14:00:00', 'PENDING', 1), -- User 1 scheduled an appointment to meet Buddy (Dog) at 2:00 PM
       (2, 2, '2024-10-15 10:30:00', 'CONFIRMED',
        2),                                         -- User 2 confirmed an appointment to meet Mittens (Cat) at 10:30 AM
       (3, 3, '2024-10-20 16:00:00', 'PENDING', 3), -- User 3 scheduled an appointment to meet Charlie (Dog) at 4:00 PM
       (4, 4, '2024-10-18 11:00:00', 'CANCELLED',
        4),                                         -- User 4 cancelled an appointment to meet Fluffy (Cat) at 11:00 AM
       (5, 5, '2024-10-25 09:00:00', 'CONFIRMED', 5);
-- User 5 confirmed an appointment to meet Rocky (Reptile) at 9:00 AM

-- liquibase formatted sql
-- changeset devandre:add_notifications_data
INSERT INTO notifications (user_id, type, message, is_read, related_entity_id)
VALUES (1, 'APPOINTMENT', 'Your appointment to meet Buddy is scheduled for 2024-10-12.', FALSE,
        1), -- User 1 gets appointment reminder for Buddy
       (2, 'ADOPTION_STATUS', 'Mittens has been successfully adopted by another user.', FALSE,
        2), -- User 2 notified of adoption status change for Mittens
       (3, 'PET_AVAILABILITY', 'Charlie is now available for adoption!', TRUE,
        3), -- User 3 notified that Charlie is available for adoption
       (4, 'APPOINTMENT', 'Your appointment with Fluffy has been cancelled.', TRUE,
        4), -- User 4 notified of cancelled appointment for Fluffy
       (5, 'ORDER', 'Your pet supplies order has been shipped!', FALSE, 5); -- User 5 notified about order shipment

