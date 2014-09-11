class CreateCustomers < ActiveRecord::Migration
  def change
    create_table :customers do |t|
      t.integer :PNR
      t.string :passenger_name
      t.string :Gender
      t.integer :Age
      t.integer :Seat_no
      t.string :Class
      t.integer :Fare
      t.string :Source_id
      t.string :Destination_id

      t.timestamps
    end
  end
end
