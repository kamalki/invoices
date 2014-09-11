class CreateCustomers < ActiveRecord::Migration
  def change
    create_table :customers do |t|
      t.string :PNR
      t.string :passenger_name
      t.string :Gender
      t.string :Age
      t.string :Seat_no
      t.string :Class
      t.string :Fare
      t.string :Source_id
      t.string :Destination_id

      t.timestamps
    end
  end
end
