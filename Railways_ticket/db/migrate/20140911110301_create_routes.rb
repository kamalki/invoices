class CreateRoutes < ActiveRecord::Migration
  def change
    create_table :routes do |t|
      t.integer :train_ID
      t.integer :stop_number
      t.integer :source_distace
      t.string :arrival_time
      t.string :departure_time

      t.timestamps
    end
  end
end
