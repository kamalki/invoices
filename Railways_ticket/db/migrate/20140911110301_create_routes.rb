class CreateRoutes < ActiveRecord::Migration
  def change
    create_table :routes do |t|
      t.integer :train_ID
      t.integer :stop_number
      t.integer :source_distace
      t.date :arrival_time
      t.date :departure_time

      t.timestamps
    end
  end
end
