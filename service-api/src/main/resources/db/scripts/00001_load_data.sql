-- liquibase formatted sql
-- changeset devandre:add_users_data
INSERT INTO users (public_id, first_name, last_name, username, email, password_hash, phone_number, address,
                   role_id, avatar_url, age, job, genre, is_enabled)
VALUES
    ('b60ff869-7c6a-4bdf-beb5-cb7b2f23bbcd', 'John', 'Doe', 'johndoe', 'johndoe@example.com',
     '$2a$12$5gI7vC7McG/gd/4F6f/fV.Z1Wy1buA1cK5wb8f9vkeXGe2q7ToCZa', '+1234567890', '1234 Elm St, Springfield, IL',
     1, 'https://example.com/avatars/johndoe.jpg', 30, 'Software Engineer', 'MALE', TRUE),

    ('ec63ad67-c730-4fc4-983d-4b801b8d8d2a', 'Jane', 'Smith', 'janesmith', 'janesmith@example.com',
     '$2a$12$V8ph2gF1pqrPQqldDXc2p.XT56Gx8yb.MKNhfzBz9B60qKZFO1A2G', '+1987654321', '5678 Oak Rd, Riverside, CA',
     2, 'https://example.com/avatars/janesmith.jpg', 28, 'Graphic Designer', 'FEMALE', TRUE),

    ('42bc8b13-4551-4b25-88b0-bbb27637decb', 'Alice', 'Johnson', 'alicejohnson', 'alicej@example.com',
     '$2a$12$FkISs8DqMtRkmfsXYzxv8kwQ1QbAnO0sGgY4u3X5RBo/O6hZ8w5iW', '+1122334455', '456 Pine Blvd, Los Angeles, CA',
     3, 'https://example.com/avatars/alicejohnson.jpg', 35, 'Product Manager', 'FEMALE', TRUE),

    ('30deaf63-937e-4d90-a01e-df413ba94a6c', 'Robert', 'Brown', 'robertbrown', 'robertb@example.com',
     '$2a$12$IBBzoqQbw5gBd.Kb13rfkQQtT4ZJngSZZYoSmbj43BfJzIruQqMxa', '+1444332211', '789 Maple St, Chicago, IL',
     2, 'https://example.com/avatars/robertbrown.jpg', 40, 'Marketing Director', 'MALE', TRUE),

    ('a3bb775f-c034-4a5b-b2f4-07a38c582fe1', 'Charlie', 'Davis', 'charliedavis', 'charlie.davis@example.com',
     '$2a$12$Gj8Bb8EwJgvY0jrV8gqJhmtxM.Z2Bz6bFjlS42yx8Ba4TmQ0Ewxe2', '+1555443322', '1234 Birch St, Miami, FL',
     1, 'https://example.com/avatars/charliedavis.jpg', 45, 'CEO', 'MALE', TRUE),

    ('a67cd823-d08a-448b-9863-594c4b926b59', 'Sophia', 'Garcia', 'sophiagarcia', 'sophia.garcia@example.com',
     '$2a$12$wck4je4dzjrBz7o25S6V6uIKb2YIwn52nCg3N12sFdHhHL9rbv9/e', '+1666777888', '876 Cedar Ave, New York, NY',
     2, 'https://example.com/avatars/sophiagarcia.jpg', 26, 'Content Writer', 'FEMALE', TRUE),

    ('ddf2c324-4e09-4378-b0ba-2e3d0bdbcb62', 'William', 'Martinez', 'william.martinez', 'williamm@example.com',
     '$2a$12$H2E9wOd8lGDL8uLMuqaJSZFY5odtA2VckA67qhbQot.klkwFf1ZpG', '+1777888999', '987 Elm Rd, Dallas, TX',
     3, 'https://example.com/avatars/williammartinez.jpg', 33, 'Sales Manager', 'MALE', TRUE),

    ('719a8a72-d0e0-4d9f-a406-b82fbb9050d0', 'Olivia', 'Wilson', 'oliviawilson', 'oliviaw@example.com',
     '$2a$12$lh8bQexZTyq0m4.HYtHbfhwRhVYN0dWjji1R2udMefSFeqEXv8RYm', '+1888999000', '101 Pine Rd, San Francisco, CA',
     2, 'https://example.com/avatars/oliviawilson.jpg', 27, 'Data Analyst', 'FEMALE', TRUE),

    ('b028fb8a-1a6a-4cb9-b174-5365d85d50f3', 'James', 'Taylor', 'jamestaylor', 'james.taylor@example.com',
     '$2a$12$64qFzB3bghbVxHga2Q/ozNoEm6ZZKNwV5QZ7Q0cZV5e/tD7NK9fTq', '+1999333555', '234 Spruce St, Seattle, WA',
     3, 'https://example.com/avatars/jamestaylor.jpg', 38, 'Software Architect', 'MALE', TRUE),

    ('42627f92-bfd1-42c7-b3f9-b4a75a9cb302', 'Emily', 'Moore', 'emilymoore', 'emily.m@example.com',
     '$2a$12$AtvyaOPaNjh9oA2A0O11a56Xz.EuQehyWoNSyToI1cPQFOG2tsR6a', '+1222333444', '456 Maple Ave, Boston, MA',
     1, 'https://example.com/avatars/emilymoore.jpg', 31, 'HR Manager', 'FEMALE', TRUE);

-- liquibase formatted sql
-- changeset devandre:add_species_data
INSERT INTO species (name, updated_by)
VALUES
    ('Dog', 1),
    ('Cat', 1),
    ('Bird', 1),
    ('Rabbit', 1),
    ('Reptile', 1);

-- liquibase formatted sql
-- changeset devandre:add_breeds_data
INSERT INTO breeds (name, species_id, updated_by)
VALUES
    ('Labrador Retriever', 1, 1),
    ('German Shepherd', 1, 1),
    ('Golden Retriever', 1, 1),
    ('Bulldog', 1, 1),
    ('Beagle', 1, 1);

-- Insert breeds data for Cat
INSERT INTO breeds (name, species_id, updated_by)
VALUES
    ('Siamese', 2, 1),
    ('Persian', 2, 1),
    ('Maine Coon', 2, 1),
    ('Bengal', 2, 1),
    ('Sphynx', 2, 1);

-- Insert breeds data for Bird
INSERT INTO breeds (name, species_id, updated_by)
VALUES
    ('Parrot', 3, 1),
    ('Canary', 3, 1),
    ('Cockatiel', 3, 1),
    ('Finch', 3, 1),
    ('Lovebird', 3, 1);

-- Insert breeds data for Rabbit
INSERT INTO breeds (name, species_id, updated_by)
VALUES
    ('Holland Lop', 4, 1),
    ('Mini Rex', 4, 1),
    ('English Angora', 4, 1),
    ('Mini Lop', 4, 1),
    ('Himalayan', 4, 1);

-- Insert breeds data for Reptile
INSERT INTO breeds (name, species_id, updated_by)
VALUES
    ('Bearded Dragon', 5, 1),
    ('Leopard Gecko', 5, 1),
    ('Ball Python', 5, 1),
    ('Chameleon', 5, 1),
    ('Iguana', 5, 1);

-- liquibase formatted sql
-- changeset devandre:add_shelters_data
INSERT INTO shelters (name, phone_number, address, email, updated_by)
VALUES
    ('Happy Tails Shelter', '+1234567890', '123 Dogwood St, Springfield, IL', 'contact@happytails.com', 1),
    ('Paws and Claws Shelter', '+1987654321', '456 Oak Rd, Riverside, CA', 'info@pawsandclaws.org', 1),
    ('The Cat Haven', '+1122334455', '789 Birch Blvd, Dallas, TX', 'adopt@thecathaven.org', 1),
    ('Animal Rescue Center', '+1444332211', '234 Pine St, New York, NY', 'info@animalrescuecenter.org', 1),
    ('The Rabbit Refuge', '+1555443322', '567 Maple Ave, Seattle, WA', 'hello@rabbitrefuge.org', 1),
    ('Reptile Rescue Sanctuary', '+1666777888', '101 Elm Rd, Miami, FL', 'contact@reptilerescuesanctuary.com', 1);

-- liquibase formatted sql
-- changeset devandre:add_pets_data
INSERT INTO pets (name, species_id, breed_id, age, genre, weight, description, size, color, health_status,
                  characteristics, adoption_status, shelter_id, arrival_date, good_with, is_spayed, updated_by)
VALUES
    ('Buddy', 1, 1, 'ADULT', 'MALE', 30.00, 'Friendly, energetic, loves to play fetch.', 'Medium', 'Golden', 'Healthy',
     'Loves children and other dogs', 'AVAILABLE', 1, '2024-09-15', 'Children, Dogs', TRUE, 1), -- Dog - Labrador
    ('Mittens', 2, 3, 'BABY', 'FEMALE', 4.50, 'Cute, loves to cuddle and play with toys.', 'Small', 'Grey', 'Healthy',
     'Prefers being the only pet', 'ADOPTED', 3, '2024-08-20', 'None', TRUE, 1),                -- Cat - Maine Coon
    ('Charlie', 1, 4, 'ADULT', 'MALE', 25.00, 'Playful, loves being around people.', 'Medium', 'Brown', 'Healthy',
     'Good with children, loves to go for walks', 'AVAILABLE', 2, '2024-10-05', 'Children, Dogs', TRUE,
     2),                                                                                        -- Dog - Bulldog
    ('Fluffy', 2, 2, 'SENIOR', 'FEMALE', 6.00, 'Quiet and calm, enjoys relaxing by windows.', 'Small', 'White', 'Healthy',
     'Good with other cats', 'AVAILABLE', 2, '2024-09-10', 'Cats', TRUE, 3),                    -- Cat - Persian
    ('Rocky', 5, 5, 'ADULT', 'MALE', 20.00, 'Independent, prefers a calm environment.', 'Medium', 'Green', 'Healthy',
     'Likes sunbathing, good with older children', 'ADOPTED', 4, '2024-06-15', 'Children', TRUE,
     1); -- Reptile - Bearded Dragon


-- liquibase formatted sql
-- changeset devandre:add_pet_images_data
INSERT INTO pet_images (pet_id, image_url)
VALUES
    (1, 'https://example.com/images/buddy_1.jpg'),  -- Buddy (Dog)
    (1, 'https://example.com/images/buddy_2.jpg'),  -- Buddy (Dog)
    (2, 'https://example.com/images/mittens_1.jpg'),  -- Mittens (Cat)
    (3, 'https://example.com/images/charlie_1.jpg'),  -- Charlie (Dog)
    (4, 'https://example.com/images/fluffy_1.jpg');  -- Fluffy (Cat)

-- liquibase formatted sql
-- changeset devandre:add_adoptions_data
INSERT INTO adoptions (pet_id, adopter_id, adoption_date, status)
VALUES
    (2, 1, '2024-09-18', 'ADOPTED'),  -- Mittens (Cat) adopted by User 1
    (5, 2, '2024-10-01', 'ADOPTED'),  -- Rocky (Reptile) adopted by User 2
    (1, 3, '2024-10-05', 'AVAILABLE'),    -- Buddy (Dog) adoption in progress by User 3
    (4, 4, '2024-09-25', 'ADOPTED'),  -- Fluffy (Cat) adopted by User 4
    (3, 5, '2024-10-10', 'AVAILABLE');    -- Charlie (Dog) adoption in progress by User 5

-- liquibase formatted sql
-- changeset devandre:add_users_favorites_data
INSERT INTO users_favorites (user_id, pet_id)
VALUES
    (1, 1),  -- User 1 marked Buddy (Dog) as a favorite
    (2, 2),  -- User 2 marked Mittens (Cat) as a favorite
    (3, 3),  -- User 3 marked Charlie (Dog) as a favorite
    (4, 4),  -- User 4 marked Fluffy (Cat) as a favorite
    (5, 5);  -- User 5 marked Rocky (Reptile) as a favorite

-- liquibase formatted sql
-- changeset devandre:add_appointments_data
INSERT INTO appointments (user_id, pet_id, appointment_date, status, created_by)
VALUES
    (1, 1, '2024-10-12 14:00:00', 'PENDING', 1),  -- User 1 scheduled an appointment to meet Buddy (Dog) at 2:00 PM
    (2, 2, '2024-10-15 10:30:00', 'CONFIRMED', 2),  -- User 2 confirmed an appointment to meet Mittens (Cat) at 10:30 AM
    (3, 3, '2024-10-20 16:00:00', 'PENDING', 3),  -- User 3 scheduled an appointment to meet Charlie (Dog) at 4:00 PM
    (4, 4, '2024-10-18 11:00:00', 'CANCELLED', 4),  -- User 4 cancelled an appointment to meet Fluffy (Cat) at 11:00 AM
    (5, 5, '2024-10-25 09:00:00', 'CONFIRMED', 5);  -- User 5 confirmed an appointment to meet Rocky (Reptile) at 9:00 AM

-- liquibase formatted sql
-- changeset devandre:add_notifications_data
INSERT INTO notifications (user_id, type, message, is_read, related_entity_id)
VALUES
    (1, 'APPOINTMENT', 'Your appointment to meet Buddy is scheduled for 2024-10-12.', FALSE, 1),  -- User 1 gets appointment reminder for Buddy
    (2, 'ADOPTION_STATUS', 'Mittens has been successfully adopted by another user.', FALSE, 2),  -- User 2 notified of adoption status change for Mittens
    (3, 'PET_AVAILABILITY', 'Charlie is now available for adoption!', TRUE, 3),  -- User 3 notified that Charlie is available for adoption
    (4, 'APPOINTMENT', 'Your appointment with Fluffy has been cancelled.', TRUE, 4),  -- User 4 notified of cancelled appointment for Fluffy
    (5, 'ORDER', 'Your pet supplies order has been shipped!', FALSE, 5);  -- User 5 notified about order shipment

