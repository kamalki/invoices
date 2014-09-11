class CreateTrainStatuses < ActiveRecord::Migration
  def change
    create_table :train_statuses do |t|
      t.string :Train_id
      t.string :Available_date
      t.string :Booked_seat1
      t.string :Booked_seat2
      t.string :Booked_seat3
      t.string :Waiting_seat1
      t.string :Waiting_seat2
      t.string :Waiting_seat3
      t.string :Available_seat1
      t.string :Avalable_seat2
      t.string :Available_seat3

      t.timestamps
    end
  end
end
