# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20140912125133) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "consists_ofs", force: true do |t|
    t.integer  "station_ID"
    t.integer  "train_ID"
    t.integer  "stop_number"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "customers", force: true do |t|
    t.integer  "PNR"
    t.string   "passenger_name"
    t.string   "Gender"
    t.integer  "Age"
    t.integer  "Seat_no"
    t.string   "Class"
    t.integer  "Fare"
    t.string   "Source_id"
    t.string   "Destination_id"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "reservation_id"
  end

  create_table "reservations", force: true do |t|
    t.string   "Email_id"
    t.integer  "Train_id"
    t.integer  "PNR"
    t.date     "Available_date"
    t.string   "Reservation_status"
    t.date     "Reservation_date"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "train_id"
    t.integer  "station_id"
  end

  create_table "routes", force: true do |t|
    t.integer  "train_ID"
    t.integer  "stop_number"
    t.integer  "source_distace"
    t.date     "arrival_time"
    t.date     "departure_time"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "train_id"
  end

  create_table "stations", force: true do |t|
    t.integer  "sation_id"
    t.string   "station_name"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "consists_of_id"
    t.integer  "routes_id"
  end

  create_table "train_statuses", force: true do |t|
    t.integer  "Train_id"
    t.date     "Available_date"
    t.string   "Booked_seat1"
    t.string   "Booked_seat2"
    t.string   "Booked_seat3"
    t.string   "Waiting_seat1"
    t.string   "Waiting_seat2"
    t.string   "Waiting_seat3"
    t.string   "Available_seat1"
    t.string   "Avalable_seat2"
    t.string   "Available_seat3"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "trains", force: true do |t|
    t.integer  "train_name"
    t.string   "train_type"
    t.integer  "seats_class1"
    t.integer  "seats_class2"
    t.integer  "fare_class1"
    t.integer  "fare_class2"
    t.integer  "available_days"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "consists_of_id"
    t.integer  "train_status_id"
  end

  create_table "trains_stations", id: false, force: true do |t|
    t.integer "train_id"
    t.integer "station_id"
  end

  create_table "users", force: true do |t|
    t.string   "name"
    t.string   "gender"
    t.integer  "age"
    t.string   "email_id"
    t.integer  "mobile"
    t.string   "city"
    t.string   "state"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end
