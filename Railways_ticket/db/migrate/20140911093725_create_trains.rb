class CreateTrains < ActiveRecord::Migration
  def change
    create_table :trains do |t|
      t.string :train_name
      t.string :train_type
      t.integer :seats_class1
      t.integer :seats_class2
      t.integer :fare_class1
      t.integer :fare_class2
      t.integer :available_days

      t.timestamps
    end
  end
end
